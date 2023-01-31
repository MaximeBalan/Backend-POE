package canard.intern.post.following.backend.service.impl;

import canard.intern.post.following.backend.dto.PoeDetailDto;
import canard.intern.post.following.backend.dto.PoeDto;
import canard.intern.post.following.backend.dto.TraineeDetailDto;
import canard.intern.post.following.backend.dto.TraineeDto;
import canard.intern.post.following.backend.entity.Poe;
import canard.intern.post.following.backend.enums.PoeType;
import canard.intern.post.following.backend.error.UpdateException;
import canard.intern.post.following.backend.repository.PoeRepository;
import canard.intern.post.following.backend.repository.TraineeRepository;
import canard.intern.post.following.backend.service.PoeService;
import canard.intern.post.following.backend.service.TraineeService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Optional;
import org.springframework.mail.javamail.MimeMessageHelper;


@Service
public class PoeServiceJpa implements PoeService {

    @Autowired
    private PoeRepository poeRepository;

    @Autowired
    private TraineeRepository traineeRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private TraineeService traineeService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    public SimpleMailMessage template;



    @Override
    public List<PoeDto> getAll() {
        return poeRepository.findAll()
                .stream()
                .map((t)->modelMapper.map(t,PoeDto.class))
                .toList();
    }

    @Override
    public Optional<PoeDetailDto> getById(int id) {
        var optPoe = poeRepository.findById(id);
        if (optPoe.isPresent()){
            var trainees = traineeRepository.findByPoeId(id)
                    .stream()
                    .map(traineeEntity -> modelMapper.map(traineeEntity, TraineeDto.class))
                    .toList();
            var poeDDto= modelMapper.map(optPoe.get(),PoeDetailDto.class);
            poeDDto.setTrainees(trainees);
            return Optional.of(poeDDto);
        }
        else{
            return Optional.empty();
        }
    }
    
    
    public List<PoeDetailDto> getByType(String type) {
    	return poeRepository.getByPoeType(type)
    			.stream()
    			.map(poeEntity -> modelMapper.map(poeEntity, PoeDetailDto.class))
    			.toList();
    }

        
    @Override
    public List<PoeDto> getByTitle(String title) {
        // TODO
        return List.of();
    }

    @Override
    public List<PoeDto> getByStartingYear(int year) {
        // TODO
        return List.of();
    }

    @Override
    public PoeDto create(PoeDto poeDto) {
        // transformer traineeDto en trainee (grace au modelMapper)
        // le sauver dans la base de donnée grace au traineeRepository.save
        // récupérer le trainee renvoyé par traineeRepository.save et le convertir en TraineeDto
        Poe poeDb;
        try {
            poeDb= poeRepository.save(modelMapper.map(poeDto, Poe.class));
        }catch(Exception e){
            throw (new UpdateException("poe couldn't be saved",e));
        }

        return modelMapper.map(poeDb, PoeDto.class);
    }

    @Override
    public Optional<PoeDto> update(int id, PoeDto poeDto) {
        poeDto.setId(id);
        var optPoeDb = poeRepository.findById(id);
        try {
            if (optPoeDb.isPresent()) {
                var poeDb = optPoeDb.get();
                modelMapper.map(poeDto, poeDb); // change traineeDb in hibernate cache
                poeRepository.flush(); // synchro et force l'update en sql
                return Optional.of(modelMapper.map(poeDb, PoeDto.class));
            } else {
                return Optional.empty();
            }
        } catch (DataIntegrityViolationException e) {
            throw new UpdateException("trainee couldn't be updated", e);
        }
    }

    @Transactional
    @Override
    public boolean delete(int id) {
        try {
            if (poeRepository.findById(id).isPresent()) {
            	//find trainees of this poes
            	//then setPoe(null) for each one
            	traineeRepository.findByPoeId(id)
            			.stream()
            			.forEach((t)->t.setPoe(null));
            	poeRepository.flush(); //force la synchronisation avec la base de données
                poeRepository.deleteById(id);
                return true;
            } else {
                return false; 
            }
        }catch(DataIntegrityViolationException e){// autres problèmes
            throw (new UpdateException("Poe couldn't be deleted",e));
        }
    }

    @Override
    public void sendSurveyMailTrainee(int idP, int idS) {

        // recuperer la liste des stagiaires d'une poe via FinById
        Optional<PoeDetailDto> poeDetailDto = getById(idP);

// faire envoi de smails a tous ces stagiaires en donnant le lien d'acces avec Id du formulaire
        for (TraineeDto trainee : poeDetailDto.get().getTrainees()) {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            String htmlMsg = "<h4>Bonjour cher stagiaire,</h4>" +
                    "<p>Suite à la formation que tu as faite à nos côtés chez Aelion, j'aimerais prendre de tes nouvelles.<br>" +
                    "    Nos merveilleux stagiaires de la POEC Java FullStack 2022-2023 ont à ce but élaboré 3 formulaires de suivi que<br> " +
                    "     je t'enverrai pour te suivre au mieux et nous permettre de perfectionner nos formations.<br>" +
                    "     Voici le premier :<br>" +
                    "</p>" +
                    "<p>" +
                    "http://localhost:4200/survey/detail/"+idS +
                    "</p>" +
                    "<p>" +
                    "    Belle continuation à toi et à bientôt ! " +
                    "</p>" +
                    "<p>" +
                    "    Jean-Luc " +
                    "</p>";
            try {
                mimeMessage.setContent(htmlMsg, "text/html");
                helper.setTo(trainee.getEmail());
                helper.setFrom("formateur.poe.aelion@gmail.com");
                helper.setSubject("Suivi des stagiaires Aelion");
                mailSender.send(mimeMessage);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }

//            message.setText("http://localhost:4200/survey/detail/"+idS);
//            emailSender.send(message);
        }

//// /** Use this or below line **/
//        helper.setText(htmlMsg, true); // Use this or above line.



        //creer une var a initialiser a la date du jour
        LocalDate dateDuJour = java.time.LocalDate.now();

        if(poeDetailDto.isPresent()) {
            PoeDetailDto poe = poeDetailDto.get();
            if (idS==1) {
                poeDetailDto.get().setSurveySendDateOneMonth(dateDuJour);
                //poeRepository.saveSendingFormDateOneMonth(dateDuJour, idP);
            }
            if (idS==2) {
                poeDetailDto.get().setSurveySendDateSixMonth(dateDuJour);
                //poeRepository.saveSendingFormDateSixMonth(dateDuJour, idP);
            }
            if (idS==3) {
                poeDetailDto.get().setSurveySendDateTwelveMonth(dateDuJour);
                //poeRepository.saveSendingFormDateTwelveMonth(dateDuJour, idP);
            }
            update(idP, poe);
        }

    }
}
