package org.screwdriver.tracker.dto;

import java.util.Map;

public class EventDTO {

    private Long id;

    private String version;

    private String submitTime;

    private String eventTimestamp;

    private Map<String, String> eventParameters;

    public EventDTO(Long id, String version, String submitTime, String eventTimestamp, Map<String, String> eventParameters) {
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

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public String getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(String eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    public Map<String, String> getEventParameters() {
        return eventParameters;
    }

    public void setEventParameters(Map<String, String> eventParameters) {
        this.eventParameters = eventParameters;
    }
}
