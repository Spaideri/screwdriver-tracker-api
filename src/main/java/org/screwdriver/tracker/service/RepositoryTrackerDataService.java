package org.screwdriver.tracker.service;

import org.screwdriver.tracker.dto.TrackerDTO;
import org.screwdriver.tracker.repository.TrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RepositoryTrackerDataService implements ITrackerDataService {

    private TrackerRepository trackerRepository;

    private MapperService mapperService;

    @Autowired
    public RepositoryTrackerDataService(TrackerRepository trackerRepository, MapperService mapperService) {
        this.trackerRepository = trackerRepository;
    }

    @Override
    @Transactional
    public List<TrackerDTO> findAll() {
        return null;
    }

}
