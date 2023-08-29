package com.huddle.huddleapidemo.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Time;

@Entity
public class Agenda extends AbstractLoggedEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int agendaID;
    @ManyToOne
    @JoinColumn(name = "MEETING_ID")
    private Meeting meeting;
    @NotNull
    private String name;
    @Size(max = 10000)
    private String description;
    @NotNull
    private Time duration;
    @NotNull
    private String purpose;
    private String notes;

    private int queueNumber;
    public String status;

    public Agenda() { }

    public Agenda(Meeting meeting, @NotNull String name, String description, @NotNull Time duration, @NotNull String purpose, int queueNumber) {
        this.meeting = meeting;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.purpose = purpose;
        this.notes = "";
        this.queueNumber = queueNumber;
        this.status = "INCOMPLETE";
    }

    public int getAgendaID() {
        return agendaID;
    }

    public void setAgendaID(int agendaID) {
        this.agendaID = agendaID;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getQueueNumber() {
        return queueNumber;
    }

    public void setQueueNumber(int queueNumber) {
        this.queueNumber = queueNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
