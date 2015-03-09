package org.screwdriver.tracker.service;


import org.joda.time.DateTimeZone;

public class TimeService {

    public TimeService() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
    }

}