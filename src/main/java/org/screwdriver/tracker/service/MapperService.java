package org.screwdriver.tracker.service;

import org.screwdriver.tracker.dto.EventDTO;
import org.screwdriver.tracker.entity.Event;
import org.screwdriver.tracker.entity.EventParameter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class MapperService {

    public List<EventDTO> mapEventsToEventDTOs(List<Event> events) {
        List<EventDTO> dtos = new ArrayList<>();
        for(Event event : events) {
            dtos.add(mapEventToEventDTO(event));
        }
        return dtos;
    }

    public EventDTO mapEventToEventDTO(Event event) {
        return new EventDTO(
                event.getId(),
                event.getVersion(),
                event.getSubmitTime(),
                event.getEventTimestamp(),
                mapEventParameters(event.getEventParameters()));
    }

    private Map<String, String> mapEventParameters(List<EventParameter> eventParameters) {
        Map<String, String> parameterMap = new TreeMap<>();
        for(EventParameter parameter : eventParameters) {
            parameterMap.put(parameter.getKey(), parameter.getValue());
        }
        return parameterMap;
    }

}
