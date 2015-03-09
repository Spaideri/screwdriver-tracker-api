package org.screwdriver.tracker.controller;

import org.screwdriver.tracker.service.IEventDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TrackerController {

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
}
