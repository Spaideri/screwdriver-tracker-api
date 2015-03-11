package org.screwdriver.tracker.service;

import org.screwdriver.tracker.dto.TrackerDTO;
import org.screwdriver.tracker.entity.Event;
import org.screwdriver.tracker.entity.Tracker;
import org.screwdriver.tracker.repository.EventRepository;
import org.screwdriver.tracker.repository.TrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepositoryTrackerDataService implements ITrackerDataService {

    private TrackerRepository trackerRepository;

    private EventRepository eventRepository;

    private MapperService mapperService;

    @Autowired
    public RepositoryTrackerDataService(TrackerRepository trackerRepository, EventRepository eventRepository, MapperService mapperService) {
        this.trackerRepository = trackerRepository;
        this.eventRepository = eventRepository;
        this.mapperService = mapperService;
    }

    @Override
    @Transactional( readOnly = true )
    public List<TrackerDTO> getAll() {
        List<TrackerDTO> dtos = new ArrayList<>();
        Iterable<Tracker> trackers = trackerRepository.findAll();
        for(Tracker tracker : trackers) {
            Event latestEvent = eventRepository.findTop1ByTrackerIdOrderByEventTimestampDesc(tracker.getId());
            dtos.add(mapperService.mapTrackerToTrackerDTO(tracker, latestEvent));
        }
        return dtos;
    }

    @Override
    public TrackerDTO getTracker(Long trackerId) {
        return mapperService.mapTrackerToTrackerDTO(
                trackerRepository.findOne(trackerId),
                eventRepository.findTop1ByTrackerIdOrderByEventTimestampDesc(trackerId));
    }

}
