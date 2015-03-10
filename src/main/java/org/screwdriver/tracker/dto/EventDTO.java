package org.screwdriver.tracker.dto;

import java.util.Date;
import java.util.Map;

public class EventDTO {

    private Long id;

    private String version;

    private Date submitTime;

    private Date eventTimestamp;

    private Map<String, String> eventParameters;

    public EventDTO(Long id, String version, Date submitTime, Date eventTimestamp, Map<String, String> eventParameters) {
        this.id = id;
        this.version = version;
        this.submitTime = submitTime;
        this.eventTimestamp = eventTimestamp;
        this.eventParameters = eventParameters;
    }

    public EventDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Date getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(Date eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    public Map<String, String> getEventParameters() {
        return eventParameters;
    }

    public void setEventParameters(Map<String, String> eventParameters) {
        this.eventParameters = eventParameters;
    }
}
