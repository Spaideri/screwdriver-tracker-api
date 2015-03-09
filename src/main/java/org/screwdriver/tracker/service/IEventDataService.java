package org.screwdriver.tracker.service;

import java.util.Map;

public interface IEventDataService {

    public void saveEvent(Map<String, String> eventData);

}
