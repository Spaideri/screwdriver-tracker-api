package org.screwdriver.tracker.entity;

import javax.persistence.Entity;

@Entity
public class ApiKey extends AbstractPersistable<Long> {

    private String apiKey;

    public ApiKey() {
    }

    public ApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
