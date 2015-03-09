package org.screwdriver.tracker.controller;

import org.screwdriver.tracker.dto.EventUpdateDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrackerController {


    @RequestMapping(
            value = "/events",
            method = RequestMethod.POST,
            headers = "content-type="+ MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String update(@RequestBody EventUpdateDTO eventUpdateDTO) {
        return "";
    }
}
