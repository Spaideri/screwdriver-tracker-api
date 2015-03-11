package org.screwdriver.tracker.service;

import org.screwdriver.tracker.dto.EventDTO;

import java.util.List;
import java.util.Map;

public interface IEventDataService {

    void saveEvent(Map<String, String> eventData);

    List<EventDTO> findEventsByTrackerId(Long trackerId);

}
