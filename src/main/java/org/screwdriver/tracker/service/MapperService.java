package org.screwdriver.tracker.service;

import org.joda.time.DateTime;
import org.screwdriver.tracker.dto.EventDTO;
import org.screwdriver.tracker.dto.TrackerDTO;
import org.screwdriver.tracker.entity.Event;
import org.screwdriver.tracker.entity.EventParameter;
import org.screwdriver.tracker.entity.Tracker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class MapperService {

    public TrackerDTO mapTrackerToTrackerDTO(Tracker tracker, Event event) {
        return new TrackerDTO(tracker.getId(), tracker.getTrackerCode(), mapEventToEventDTO(event));
    }

    public List<EventDTO> mapEventsToEventDTOs(List<Event> events) {
        List<EventDTO> dtos = new ArrayList<>();
        if(null != events) {
            for(Event event : events) {
                dtos.add(mapEventToEventDTO(event));
            }
        }
        return dtos;
    }

    public EventDTO mapEventToEventDTO(Event event) {
        EventDTO dto = null;
        if(null != event) {
            dto = new EventDTO(
                    event.getId(),
                    event.getVersion(),
                    new DateTime(event.getSubmitTime()).toDateTimeISO().toString(),
                    new DateTime(event.getEventTimestamp()).toDateTimeISO().toString(),
                    mapEventParameters(event.getEventParameters()));
        }
        return dto;
    }

    private Map<String, String> mapEventParameters(List<EventParameter> eventParameters) {
        Map<String, String> parameterMap = new TreeMap<>();
        for(EventParameter parameter : eventParameters) {
            parameterMap.put(parameter.getKey(), parameter.getValue());
        }
        return parameterMap;
    }

}
