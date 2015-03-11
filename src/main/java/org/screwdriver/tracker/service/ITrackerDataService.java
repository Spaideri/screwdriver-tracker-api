package org.screwdriver.tracker.service;


import org.screwdriver.tracker.dto.TrackerDTO;

import java.util.List;

public interface ITrackerDataService {

    List<TrackerDTO> getAll();

    TrackerDTO getTracker(Long trackerId);

}
