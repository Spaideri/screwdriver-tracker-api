package org.screwdriver.tracker.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class EventParameter extends AbstractPersistable<Long> {

    @ManyToOne
    private Event event;

    private String key;

    private String value;

    private EventParameter(Builder builder) {
        super.setId(builder.id);
        this.event = builder.event;
        this.key = builder.key;
        this.value = builder.value;
    }

    public EventParameter() {
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static class Builder {
        private Long id;
        private Event event;
        private String key;
        private String value;

        public EventParameter build() {
            return new EventParameter(this);
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder event(Event event) {
            this.event = event;
            return this;
        }

        public Builder key(String key) {
            this.key = key;
            return this;
        }

        public Builder value(String value) {
            this.value = value;
            return this;
        }
    }
}
