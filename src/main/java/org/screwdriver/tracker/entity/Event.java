package org.screwdriver.tracker.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Event extends AbstractPersistable<Long> {

    @ManyToOne
    private Tracker tracker;

    private String version;

    @OneToMany(mappedBy = "event")
    private List<EventParameter> eventParameters;

    @Temporal(TemporalType.TIMESTAMP)
    private Date submitTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date eventTimestamp;

    @PrePersist
    protected void onCreate() {
        submitTime = new Date();
    }

    public Event() {
    }

    private Event(Builder builder) {
        super.setId(builder.id);
        this.tracker = builder.tracker;
        this.version = builder.version;
        this.eventParameters = builder.eventParameters;
        this.eventTimestamp = builder.eventTimestamp;
        this.submitTime = builder.submitTime;
    }

    public Tracker getTracker() {
        return tracker;
    }

    public void setTracker(Tracker tracker) {
        this.tracker = tracker;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<EventParameter> getEventParameters() {
        return eventParameters;
    }

    public void setEventParameters(List<EventParameter> eventParameters) {
        this.eventParameters = eventParameters;
    }

    public Date getSubmitTime() {
        return this.submitTime;
    }

    public Date getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(Date eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    public static class Builder {
        private Long id;
        private Tracker tracker;
        private String version;
        private List<EventParameter> eventParameters;
        private Date eventTimestamp;
        private Date submitTime;

        public Event build() {
            return new Event(this);
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder tracker(Tracker tracker) {
            this.tracker = tracker;
            return this;
        }

        public Builder version(String version) {
            this.version = version;
            return this;
        }

        public Builder eventParameters(List<EventParameter> eventParameters) {
            this.eventParameters = eventParameters;
            return this;
        }

        public Builder eventTimestamp(Date eventTimestamp) {
            this.eventTimestamp = eventTimestamp;
            return this;
        }

        public Builder submitTime(Date submitTime) {
            this.submitTime = submitTime;
            return this;
        }
    }
}
