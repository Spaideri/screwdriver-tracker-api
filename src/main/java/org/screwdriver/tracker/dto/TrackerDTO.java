package org.screwdriver.tracker.dto;

public class TrackerDTO {

    private Long id;

    private String trackerCode;

    private EventDTO latestEvent;

    public TrackerDTO(Long id, String trackerCode, EventDTO latestEvent) {
        this.id = id;
        this.trackerCode = trackerCode;
        this.latestEvent = latestEvent;
    }

    public TrackerDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrackerCode() {
        return trackerCode;
    }

    public void setTrackerCode(String trackerCode) {
        this.trackerCode = trackerCode;
    }

    public EventDTO getLatestEvent() {
        return latestEvent;
    }

    public void setLatestEvent(EventDTO latestEvent) {
        this.latestEvent = latestEvent;
    }
}
