package org.screwdriver.tracker.controller;

import org.screwdriver.tracker.dto.TrackerDTO;
import org.screwdriver.tracker.service.ITrackerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/secure")
public class SecureTrackerController {

    @Autowired
    private ITrackerDataService trackerDataService;

    @RequestMapping(
            value = "/trackers",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TrackerDTO> getAllTrackers() {
        return trackerDataService.getAll();
    }

    @RequestMapping(
            value = "/trackers/{trackerId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public TrackerDTO getTrackerByTrackerId(@PathVariable("trackerId") Long trackerId) {
        return trackerDataService.getTracker(trackerId);
    }
}
