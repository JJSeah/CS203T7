package com.huddle.huddleapidemo.services;

import com.huddle.huddleapidemo.domain.*;
import com.huddle.huddleapidemo.components.ChangeTracker;
import com.huddle.huddleapidemo.repositories.*;
import com.huddle.huddleapidemo.structures.AgendaResponse;
import com.huddle.huddleapidemo.websockets.MeetingSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class AgendaService {
    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private BrainstormIdeaRepository brainstormIdeaRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private PresenterRepository presenterRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AgendaFileRepository agendaFileRepository;

    @Autowired
    private AgendaResourceRepository agendaResourceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private SMSService smsService;

    @Autowired
    private TelegramService TelegramService;

    @Autowired
    private ChangeTracker changeTracker;

    @Autowired
    private MeetingSession meetingSession;

    private static final Logger log = LoggerFactory.getLogger(AgendaService.class);

    private DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy hh:mm");

    //Create
    public int addAgenda(Agenda agenda, int meetingID){
        agenda.setQueueNumber(agendaRepository.getAgendaCount(meetingID)+1);
        agenda.setMeeting(meetingRepository.findByMeetingID(meetingID));
        agenda.setStatus("INCOMPLETE");
        agendaRepository.save(agenda);
        return agenda.getAgendaID();
    }

    //Read
    public Iterable<AgendaResponse> getUserAgendaByMeeting(int meetingID, int userID){
        ArrayList<AgendaResponse> agendaResponses = new ArrayList<>();
        agendaRepository.getUserAgendaByMeeting(meetingID, userID)
                .forEach(agenda -> {
                    agendaResponses.add(convertAgendaToAgendaResponse(agenda, userID));
//            agendaResponses.add(new AgendaResponse(agenda.getCreatedBy(), agenda.getCreatedOn(),
//                    agenda.getLastModifiedBy(), agenda.getLastModifiedOn(), agenda.getAgendaID(),
//                    agenda.getMeeting().getMeetingID(), agenda.getName(), agenda.getDescription(),
//                    agenda.getDuration(), agenda.getPurpose(), agenda.getNotes(), agenda.getQueueNumber(),
//                    agenda.getStatus(), isPresenter(agenda.getAgendaID(), userID)));
        });
        return agendaResponses;
    }

    public Page<AgendaResponse> getUserAgendaByMeeting_Page(int meetingID, int userID, Pageable pageable){
        return agendaRepository.getUserAgendaByMeeting_Page(meetingID, userID, pageable).map(agenda -> convertAgendaToAgendaResponse(agenda, userID));
    }

    //Update
    public void updateAgenda(Agenda agenda, int agendaID) {
        log.info("Updating Agenda ID : " + agendaID);
        Agenda agendaToUpdate = agendaRepository.findAgendaByAgendaID(agendaID);
        Agenda firstAgendaInLine = agendaRepository.getAgendaByMeeting_MeetingIDAndQueueNumber(agendaToUpdate.getMeeting().getMeetingID(), agendaToUpdate.getQueueNumber() + 1);
        Agenda secondAgendaInLine = agendaRepository.getAgendaByMeeting_MeetingIDAndQueueNumber(agendaToUpdate.getMeeting().getMeetingID(), agendaToUpdate.getQueueNumber() + 2);
        Agenda thirdAgendaInLine = agendaRepository.getAgendaByMeeting_MeetingIDAndQueueNumber(agendaToUpdate.getMeeting().getMeetingID(), agendaToUpdate.getQueueNumber() + 3);

        if(agenda.getStatus() != null) {
            log.info("Status is not null");
            //Check status is ongoing and remind next presenter to prepare and subsequent presenter to get ready.
            if (agenda.getStatus().equals("ONGOING")) {
                log.info("Status Change to Ongoing");
                agendaToUpdate.setStatus("ONGOING");
                //Case for first presenter in line
                if(firstAgendaInLine != null) {
                    log.info("Next Agenda : " + firstAgendaInLine.getAgendaID());
                    presenterRepository.findPresentersByPresenterID_Agenda(firstAgendaInLine)
                            .forEach(presenter -> {
                                log.info("First Group Of Agenda Presenters Found : Now sending email");
                                if(presenter.getPresenterID().getUser().isEmailNotification()) {
                                    emailService.sendSimpleMessage(presenter.getPresenterID().getUser().getEmail(), "[Reminder] Presentation for Agenda : " + firstAgendaInLine.getName(),
                                                    "You are the first presenter in line. Please prepare to get ready for your presentation");
                                }
                                if (presenter.getPresenterID().getUser().isSmsNotification()) {
                                    smsService.sendSMS(presenter.getPresenterID().getUser().getPhone(),
                                            "[Reminder] Presentation for Agenda : " + firstAgendaInLine.getName() +
                                                    "\nYou are the first presenter in line. Please prepare to get ready for your presentation");
                                }
                                if (presenter.getPresenterID().getUser().isTelegramNotification()) {
                                    TelegramService.sendTelegramMessage(presenter.getPresenterID().getUser().getPhone(),
                                            "[Reminder] Presentation for Agenda : " + firstAgendaInLine.getName() +
                                                    "\nYou are the first presenter in line. Please prepare to get ready for your presentation");
                                }
                            });
                }
                //Case for second presenter in line
                if (secondAgendaInLine != null) {
                    presenterRepository.findPresentersByPresenterID_Agenda(secondAgendaInLine)
                            .forEach(presenter -> {
                                if(presenter.getPresenterID().getUser().isEmailNotification()) {
                                    log.info("Second Group Of Agenda Presenters Found : Now sending email");
                                    emailService.sendSimpleMessage(presenter.getPresenterID().getUser().getEmail(), "[Reminder] Presentation for Agenda : " + secondAgendaInLine.getName(),
                                                    "You that you are the second presenter in line. Please standby for your presentation");
                                }
                                if (presenter.getPresenterID().getUser().isSmsNotification()) {
                                    smsService.sendSMS(presenter.getPresenterID().getUser().getPhone(),
                                            "[Reminder] Presentation for Agenda : " + secondAgendaInLine.getName() +
                                                    "\nYou are the second presenter in line. Please standby for your presentation");
                                }
                                if (presenter.getPresenterID().getUser().isTelegramNotification()) {
                                    TelegramService.sendTelegramMessage(presenter.getPresenterID().getUser().getPhone(),
                                            "[Reminder] Presentation for Agenda : " + secondAgendaInLine.getName() +
                                                    "\nYou are the second presenter in line. Please standby for your presentation");
                                }
                            });
                }
                //Case for third presenter in line
                if (thirdAgendaInLine != null) {
                    presenterRepository.findPresentersByPresenterID_Agenda(thirdAgendaInLine)
                            .forEach(presenter -> {
                                if(presenter.getPresenterID().getUser().isEmailNotification()) {
                                    log.info("Third Group Of Agenda Presenters Found : Now sending email");
                                    emailService.sendSimpleMessage(presenter.getPresenterID().getUser().getEmail(), "[Reminder] Presentation for Agenda : " + thirdAgendaInLine.getName(),
                                                    "You are the third presenter in line. Please prepare to standby for your presentation");
                                    }
                                if (presenter.getPresenterID().getUser().isSmsNotification()) {
                                    smsService.sendSMS(presenter.getPresenterID().getUser().getPhone(),
                                            "[Reminder] Presentation for Agenda : " + thirdAgendaInLine.getName() +
                                                    "\nYou are the third presenter in line. Please prepare to standby for your presentation");
                                }
                                if (presenter.getPresenterID().getUser().isTelegramNotification()) {
                                    TelegramService.sendTelegramMessage(presenter.getPresenterID().getUser().getPhone(),
                                            "[Reminder] Presentation for Agenda : " + thirdAgendaInLine.getName() +
                                                    "\nYou are the third presenter in line. Please prepare to standby for your presentation");
                                }
                            });
                }

            }
            //Check status is completed and remind preparing team to come in.
            if (agenda.getStatus().equals("COMPLETE")) {
                agendaToUpdate.setStatus("COMPLETE");
                //Check if there are any presenters left
                log.info("Agenda ID : " + agendaToUpdate.getAgendaID() + "is complete");
                if (agendaRepository.getAgendaByMeeting_MeetingIDAndQueueNumber(agendaToUpdate.getMeeting().getMeetingID(), agendaToUpdate.getQueueNumber() + 1) != null) {
                    log.info("There is still one more agenda");
                    presenterRepository.findPresentersByPresenterID_Agenda(agendaRepository
                            .getAgendaByMeeting_MeetingIDAndQueueNumber(agendaToUpdate.getMeeting().getMeetingID(), agendaToUpdate.getQueueNumber() + 1))
                            .forEach(presenter -> {
                                log.info("Found presenter");
                                if(presenter.getPresenterID().getUser().isEmailNotification()) {
                                    emailService.sendSimpleMessage("kevintoh0305@gmail.com", "[Reminder] Presentation for Agenda : " + agendaToUpdate.getName(),
                                                    "You that you may come in to present now.");
                                }
                                if (presenter.getPresenterID().getUser().isSmsNotification()) {
                                    smsService.sendSMS(presenter.getPresenterID().getUser().getPhone(),
                                            "[Reminder] Presentation for Agenda : " + agendaToUpdate.getName() +
                                                    "\nYou that you may come in to present now.");
                                }
                                if (presenter.getPresenterID().getUser().isTelegramNotification()) {
                                    TelegramService.sendTelegramMessage(presenter.getPresenterID().getUser().getPhone(),
                                            "[Reminder] Presentation for Agenda : " + agendaToUpdate.getName() +
                                                    "\nYou that you may come in to present now.");
                                }
                            });
                }
            }
            agendaRepository.save(agendaToUpdate);
        }
        else{
            // Update Agenda Details Request Body does not have a value for status.
            String changes = changeTracker.compare(agendaToUpdate, agenda);
            if(!changes.equals("")) {
                updateAgendaDetails(agendaToUpdate ,agenda, changes);
            }
        }
    }

    public void updateOrderOfAgendasInMeeting(ArrayList<Agenda> agendasList, int meetingID){
        for (Agenda agenda : agendasList) {
            Agenda agendaToUpdate = agendaRepository.findAgendaByAgendaID(agenda.getAgendaID());
            agendaToUpdate.setQueueNumber(agenda.getQueueNumber());
            agendaRepository.save(agendaToUpdate);
        }

        // Get the approximate new timing of the updated agenda
        Meeting meeting = meetingRepository.findByMeetingID(meetingID);
        Date updatedDateTime = meeting.getStartTime();
        for(int i = 1; i <= agendasList.size(); i++){
            Agenda updatedAgenda = agendaRepository.getAgendaByMeeting_MeetingIDAndQueueNumber(meetingID, i);
            // Remind Presenters of changes
            Date finalUpdatedDateTime = updatedDateTime;
            presenterRepository.findPresentersByPresenterID_Agenda(updatedAgenda).forEach(presenter -> {
                //Email
                //Check if email notifications is enabled then send
                log.info("Email sent to presenter : " + presenter.getPresenterID().getUser().getEmail());
                log.info("SMS sent to presenter : " + presenter.getPresenterID().getUser().getPhone());
                emailService.sendSimpleMessage("kevintoh0305@gmail.com", "[Important] Change in presentation schedule for agenda : " + updatedAgenda.getName(),
                        UpdatedOrderOfAgendaNotificationPresenter(updatedAgenda.getName(), finalUpdatedDateTime));
                if (presenter.getPresenterID().getUser().isEmailNotification()) {
                    emailService.sendSimpleMessage(presenter.getPresenterID().getUser().getEmail(), "[Important] Change in presentation schedule for agenda : " + updatedAgenda.getName(),
                            UpdatedOrderOfAgendaNotificationPresenter(updatedAgenda.getName(), finalUpdatedDateTime));
                }
                //SMS
                //Check if sms notifications is enabled then send
                if (presenter.getPresenterID().getUser().isSmsNotification()) {
                    smsService.sendSMS(presenter.getPresenterID().getUser().getPhone(),
                            UpdatedOrderOfAgendaNotificationPresenter(updatedAgenda.getName(), finalUpdatedDateTime));
                }
                if (presenter.getPresenterID().getUser().isTelegramNotification()) {
                    TelegramService.sendTelegramMessage(presenter.getPresenterID().getUser().getPhone(),
                            UpdatedOrderOfAgendaNotificationPresenter(updatedAgenda.getName(), finalUpdatedDateTime));
                }
            });
            //Remind Participants of changes
            participantRepository.findParticipantsByParticipantID_Agenda(updatedAgenda).forEach(participant -> {
                if (participant.getParticipantID().getUser().isEmailNotification()) {
                    emailService.sendSimpleMessage(participant.getParticipantID().getUser().getEmail(), "[Important] Change in presentation schedule for agenda : " + updatedAgenda.getName(),
                            UpdatedOrderOfAgendaNotificationParticipant(updatedAgenda.getName(), finalUpdatedDateTime));
                }
                if (participant.getParticipantID().getUser().isSmsNotification()) {
                    smsService.sendSMS(participant.getParticipantID().getUser().getPhone(),
                            UpdatedOrderOfAgendaNotificationParticipant(updatedAgenda.getName(), finalUpdatedDateTime));
                }
                if (participant.getParticipantID().getUser().isTelegramNotification()) {
                    TelegramService.sendTelegramMessage(participant.getParticipantID().getUser().getPhone(),
                            UpdatedOrderOfAgendaNotificationParticipant(updatedAgenda.getName(), finalUpdatedDateTime));
                }

            });
            try {
                updatedDateTime = new Date(updatedDateTime.getTime() + meetingSession.SQLTimeToJavaTimeInLong(updatedAgenda.getDuration()));
            }
            catch (ParseException e){
                e.printStackTrace();
            }
        }
    }

    public void updateAgendaDetails(Agenda agendaToUpdate, Agenda agenda, String changes){
        //Update
        agendaToUpdate.setLastModifiedBy(agenda.getLastModifiedBy());
        agendaToUpdate.setName(agenda.getName());
        agendaToUpdate.setDescription(agenda.getDescription());
        agendaToUpdate.setDuration(agenda.getDuration());
        agendaToUpdate.setPurpose(agenda.getPurpose());
        agendaRepository.save(agendaToUpdate);
        //Send emails for changes
        //Presenters
        presenterRepository.findPresentersByPresenterID_Agenda(agendaToUpdate).forEach(presenter -> {
            if(presenter.getPresenterID().getUser().isEmailNotification()){
                log.info("Sent Email");
                try {
                    emailService.sendHTMLMessage(presenter.getPresenterID().getUser().getEmail(),
                            "[Important] Updates to Agenda : " + agendaToUpdate.getName(),
                            emailService.agendaUpdateTemplate(changes));
                }
                catch(MessagingException e){
                    e.printStackTrace();
                }
            }
            if(presenter.getPresenterID().getUser().isSmsNotification()){
                log.info("Sent SMS");
                smsService.sendSMS(presenter.getPresenterID().getUser().getPhone(),
                        "[Important] Updates to Agenda : " + agendaToUpdate.getName() +
                                "\n This message is to inform you that the details of the agenda " + agendaToUpdate.getName() + "has been modified." +
                                "Please log in to view the changes");
            }
            if(presenter.getPresenterID().getUser().isTelegramNotification()){
                log.info("Sent Telegram");
                TelegramService.sendTelegramMessage(presenter.getPresenterID().getUser().getPhone(),
                        "[Important] Updates to Agenda : " + agendaToUpdate.getName() +
                                "\n This message is to inform you that the details of the agenda " + agendaToUpdate.getName() + "has been modified." +
                                "Please log in to view the changes");
            }
        });
        //Participants
        participantRepository.findParticipantsByParticipantID_Agenda(agendaToUpdate).forEach(participant -> {
            if(participant.getParticipantID().getUser().isEmailNotification()){
                try {
                    emailService.sendHTMLMessage(participant.getParticipantID().getUser().getEmail(),
                            "[Important] Updates to Agenda : " + agendaToUpdate.getName(),
                            emailService.agendaUpdateTemplate(changes));
                }
                catch(MessagingException e){
                    e.printStackTrace();
                }
            }
            if(participant.getParticipantID().getUser().isSmsNotification()){
                smsService.sendSMS(participant.getParticipantID().getUser().getPhone(),
                        "[Important] Updates to Agenda : " + agendaToUpdate.getName() +
                                "\nThe details of the agenda " + agendaToUpdate.getName() + "has been modified." +
                                "Please log in to view the changes");
            }
            if(participant.getParticipantID().getUser().isTelegramNotification()){
                TelegramService.sendTelegramMessage(participant.getParticipantID().getUser().getPhone(),
                        "[Important] Updates to Agenda : " + agendaToUpdate.getName() +
                                "\nThe details of the agenda " + agendaToUpdate.getName() + "has been modified." +
                                "Please log in to view the changes");
            }
        });
    }

    //Delete
    public void deleteAgenda(int id){
        Agenda agenda = agendaRepository.findAgendaByAgendaID(id);
        brainstormIdeaRepository.findAllByAgenda(agenda).forEach(brainstormIdea -> brainstormIdeaRepository.delete(brainstormIdea));
        participantRepository.findParticipantsByParticipantID_Agenda(agenda).forEach(participant -> participantRepository.delete(participant));
        presenterRepository.findPresentersByPresenterID_Agenda(agenda).forEach(presenter -> presenterRepository.delete(presenter));
        questionRepository.findQuestionsByAgenda(agenda).forEach(question -> questionRepository.delete(question));
        agendaFileRepository.findAllByAgenda(agenda).forEach(agendaFile -> agendaFileRepository.delete(agendaFile));
        agendaResourceRepository.findAgendaResourcesByAgenda(agenda).forEach(agendaResource -> agendaResourceRepository.delete(agendaResource));
        agendaRepository.delete(agenda);
    }

    //Others

    public boolean isPresenter(int agendaID, int userID) {
        Agenda agenda = agendaRepository.findAgendaByAgendaID(agendaID);
        User user =  userRepository.findUserByUserID(userID);
        return presenterRepository.findPresenterByPresenterID_AgendaAndPresenterID_User(agenda, user) != null;
    }

    public String UpdatedOrderOfAgendaNotificationPresenter(String agendaName, Date updatedTime){
        return  "[Important] Change in presentation schedule for agenda : " + agendaName +
                "\nPlease take note that the schedule for the presentation you are making for the agenda " + agendaName + " has been changed." +
                "\nIt has been shifted to " + dateFormat.format(updatedTime);
    }

    public String UpdatedOrderOfAgendaNotificationParticipant(String agendaName, Date updatedTime){
        return  "[Important] Change in presentation schedule for agenda : " + agendaName +
                "\nPlease take note that the schedule for the agenda " + agendaName + " that you will be attending has been changed." +
                "\nIt has been shifted to " + dateFormat.format(updatedTime);
    }

    public AgendaResponse convertAgendaToAgendaResponse(Agenda agenda, int userID){
        return new AgendaResponse(agenda.getCreatedBy(), agenda.getCreatedOn(),
                agenda.getLastModifiedBy(), agenda.getLastModifiedOn(), agenda.getAgendaID(),
                agenda.getMeeting().getMeetingID(), agenda.getName(), agenda.getDescription(),
                agenda.getDuration(), agenda.getPurpose(), agenda.getNotes(), agenda.getQueueNumber(),
                agenda.getStatus(), isPresenter(agenda.getAgendaID(), userID));
    }

}
