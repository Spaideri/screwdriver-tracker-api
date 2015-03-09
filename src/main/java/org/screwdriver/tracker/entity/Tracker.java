package org.screwdriver.tracker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Tracker extends AbstractPersistable<Long> {

    @Column(unique = true)
    private String trackerCode;

    @OneToMany
    private List<Event> events;

    private Tracker(Builder builder) {
        super.setId(builder.id);
        this.trackerCode = builder.trackerCode;
    }

    public Tracker() {
    }

    public static class Builder {
        private Long id;
        private String trackerCode;

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
    }
}
