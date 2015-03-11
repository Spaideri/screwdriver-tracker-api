package org.screwdriver.tracker.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Tracker extends AbstractPersistable<Long> {

    @Column(unique = true)
    private String trackerCode;

    @OneToMany(mappedBy = "tracker", cascade = CascadeType.ALL)
    private List<Event> events;

    private Tracker(Builder builder) {
        super.setId(builder.id);
        this.trackerCode = builder.trackerCode;
        this.events = builder.events;
    }

    public Tracker() {
    }

    public String getTrackerCode() {
        return trackerCode;
    }

    public void setTrackerCode(String trackerCode) {
        this.trackerCode = trackerCode;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public static class Builder {
        private Long id;
        private String trackerCode;
        private List<Event> events;

        public Tracker build() {
            return new Tracker(this);
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder trackerCode(String trackerCode) {
            this.trackerCode = trackerCode;
            return this;
        }

        public Builder events(List<Event> events) {
            this.events = events;
            return this;
        }
    }
}
