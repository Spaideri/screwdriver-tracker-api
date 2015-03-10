package org.screwdriver.tracker.service;

import org.screwdriver.tracker.dto.EventDTO;

import java.util.List;
import java.util.Map;

public interface IEventDataService {

    public void saveEvent(Map<String, String> eventData);

    public List<EventDTO> findEventsByTrackerId(Long trackerId);

}
