package com.huddle.huddleapidemo.controllers;

import com.huddle.huddleapidemo.domain.Agenda;
import com.huddle.huddleapidemo.repositories.AgendaRepository;
import com.huddle.huddleapidemo.services.AgendaService;
import com.huddle.huddleapidemo.structures.AgendaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/agenda")
public class AgendaController {
    @Autowired
    private AgendaService agendaService;

    @Autowired
    private AgendaRepository agendaRepository;

    @GetMapping("/meeting/{meetingID}/user/{userID}")
    public Iterable<AgendaResponse> getAgendaForMeeting(@PathVariable("meetingID") int meetingID,
                                                        @PathVariable("userID") int userID){
        return agendaService.getUserAgendaByMeeting(meetingID, userID);
    }

    @GetMapping("/meeting/{meetingID}/user/{userID}/page")
    public Iterable<AgendaResponse> getAgendaForMeeting_Page(@PathVariable("meetingID") int meetingID,
                                                             @PathVariable("userID") int userID,
                                                             @RequestParam Optional<Integer> page,
                                                             @RequestParam Optional<Integer> size){
        return agendaService.getUserAgendaByMeeting_Page(meetingID, userID, PageRequest.of(page.orElse(0),size.orElse(5)));
    }

    @GetMapping("/meeting/{meetingID}/queue/{queueNo}")
    public Agenda getAgendaByMeetingAndQueueNo(@PathVariable("meetingID") int meetingID,
                                               @PathVariable("queueNo") int queueNo){
        return agendaRepository.getAgendaByMeeting_MeetingIDAndQueueNumber(meetingID, queueNo);
    }

    @PostMapping("/add/{meetingID}")
    public int addAgenda(@RequestBody Agenda agenda, @PathVariable("meetingID") int meetingID, HttpServletResponse response){
        response.setStatus(HttpServletResponse.SC_CREATED);
        return agendaService.addAgenda(agenda, meetingID);
    }

    @PutMapping("/updateorderofagendas/{meetingID}")
    public void addAgendasToMeeting(@RequestBody ArrayList<Agenda> agendasList, @PathVariable("meetingID") int meetingID){
        agendaService.updateOrderOfAgendasInMeeting(agendasList, meetingID);
    }

    @PatchMapping("/update/{agendaID}")
    public void updateAgenda(@RequestBody Agenda agenda, @PathVariable("agendaID") int agendaID){
        agendaService.updateAgenda(agenda, agendaID);
    }

    @DeleteMapping("/deleteagenda/{id}")
    public void deleteAgenda(@PathVariable("id") int id){
        agendaService.deleteAgenda(id);
    }
}
