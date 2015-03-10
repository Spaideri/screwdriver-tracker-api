package org.screwdriver.tracker.controller;

import org.screwdriver.tracker.dto.EventDTO;
import org.screwdriver.tracker.service.IEventDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class EventsController {

    @Autowired
    private IEventDataService eventDataService;

    @RequestMapping(
            value = "/events",
            method = RequestMethod.POST,
            headers = "content-type="+ MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String update(@RequestBody Map<String, String> eventData) {
        eventDataService.saveEvent(eventData);
        return "{\"status\":\"ok\"}";
    }

    @RequestMapping(
            value = "/trackers/{trackerId}/events",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EventDTO> update(@PathVariable("trackerId") Long trackerId) {
        return eventDataService.findEventsByTrackerId(trackerId);
    }
}
