package com.huddle.huddleapidemo.repositories;

import com.huddle.huddleapidemo.domain.Agenda;
import com.huddle.huddleapidemo.domain.Meeting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "agendas", path="agendas")
public interface AgendaRepository extends CrudRepository<Agenda, Integer> {

    Agenda findAgendaByAgendaID(int id);

    Agenda getAgendaByMeeting_MeetingIDAndQueueNumber(int meetingID, int queueNumber);

    Iterable<Agenda> findAllByMeeting(Meeting meeting);

    Iterable<Agenda> findAllByMeetingOrderByQueueNumberAsc(Meeting meeting);

    @Query(value = "select * from agenda " +
            "where agendaid in " +
            "(select agendaid from agenda " +
            "inner join participants on participants.agenda_id = agenda.agendaid " +
            "where agenda.meeting_id = :meetingID and participants.user_id = :userID) " +
            "union " +
            "select * from agenda " +
            "where agendaid in " +
            "(select agendaid from agenda " +
            "inner join meeting on agenda.meeting_id = meeting.meetingid " +
            "inner join super_meeting_user on meeting.meetingid = super_meeting_user.meeting_id " +
            "where agenda.meeting_id = :meetingID and super_meeting_user.user_id = :userID) " +
            "union " +
            "select * from agenda where agendaid in " +
            "(select agendaid from agenda " +
            "inner join presenters on presenters.agenda_id = agenda.agendaid " +
            "where agenda.meeting_id = :meetingID and presenters.user_id = :userID) " +
            "order by queue_number asc", nativeQuery = true)
    Iterable<Agenda> getUserAgendaByMeeting(@Param("meetingID") int meetingID, @Param("userID") int userID);

    @Query(value = "select * from agenda " +
            "where agendaid in" +
            "(select agendaid from agenda " +
            "inner join meeting on agenda.meeting_id = meeting.meetingid " +
            "inner join super_meeting_user on meeting.meetingid = super_meeting_user.meeting_id " +
            "full outer join participants on participants.agenda_id = agenda.agendaid " +
            "full outer join presenters on presenters.agenda_id = agenda.agendaid " +
            "where (agenda.meeting_id = :meetingID " +
            "and participants.user_id = :userID " +
            "or super_meeting_user.user_id = :userID " +
            "or presenters.user_id = :userID)) " +
            "order by queue_number asc", nativeQuery = true)
    Page<Agenda> getUserAgendaByMeeting_Page(@Param("meetingID") int meetingID, @Param("userID") int userID, Pageable pageable);

    @Override
    List<Agenda> findAll();

    @Query(value = "select count(queue_number) from agenda " +
            "inner join meeting on agenda.meeting_id = meeting.meetingid " +
            "where meeting.meetingid = :meetingID", nativeQuery = true)
    Integer getAgendaCount(@Param("meetingID") Integer meetingID);

    @Override
    void delete(Agenda agenda);
}
