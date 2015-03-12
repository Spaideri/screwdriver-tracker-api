package org.screwdriver.tracker.controller;

import org.screwdriver.tracker.dto.EventDTO;
import org.screwdriver.tracker.service.IEventDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/secure")
public class SecureEventsController {

    @Autowired
    private IEventDataService eventDataService;

    @RequestMapping(
            value = "/trackers/{trackerId}/events",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EventDTO> findEventsByTrackerId(@PathVariable("trackerId") Long trackerId) {
        return eventDataService.findEventsByTrackerId(trackerId);
    }

}
