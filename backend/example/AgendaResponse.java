package com.huddle.huddleapidemo.structures;

import com.huddle.huddleapidemo.domain.AbstractLoggedEntity;

import java.sql.Time;
import java.util.Date;

public class AgendaResponse extends AbstractLoggedEntity {
    private int agendaID;
    private int meetingID;
    private String name;
    private String description;
    private Time duration;
    private String purpose;
    private String notes;
    private int queueNumber;
    private String status;
    private boolean isPresenter;

    public AgendaResponse() {
    }

    public AgendaResponse(int agendaID, int meetingID, String name, String description, Time duration, String purpose, String notes, int queueNumber, String status) {
        this.agendaID = agendaID;
        this.meetingID = meetingID;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.purpose = purpose;
        this.notes = notes;
        this.queueNumber = queueNumber;
        this.status = status;
    }

    public AgendaResponse(int agendaID, int meetingID, String name, String description, Time duration, String purpose, String notes, int queueNumber, String status, boolean isPresenter) {
        this.agendaID = agendaID;
        this.meetingID = meetingID;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.purpose = purpose;
        this.notes = notes;
        this.queueNumber = queueNumber;
        this.status = status;
        this.isPresenter = isPresenter;
    }

    public AgendaResponse(String createdBy, Date createdOn, String lastModifiedBy, Date lastModifiedOn, int agendaID, int meetingID, String name, String description, Time duration, String purpose, String notes, int queueNumber, String status, boolean isPresenter) {
        super(createdBy, createdOn, lastModifiedBy, lastModifiedOn);
        this.agendaID = agendaID;
        this.meetingID = meetingID;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.purpose = purpose;
        this.notes = notes;
        this.queueNumber = queueNumber;
        this.status = status;
        this.isPresenter = isPresenter;
    }

    public int getAgendaID() {
        return agendaID;
    }

    public void setAgendaID(int agendaID) {
        this.agendaID = agendaID;
    }

    public int getMeetingID() {
        return meetingID;
    }

    public void setMeetingID(int meetingID) {
        this.meetingID = meetingID;
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

    public boolean isPresenter() {
        return isPresenter;
    }

    public void setPresenter(boolean presenter) {
        isPresenter = presenter;
    }
}
