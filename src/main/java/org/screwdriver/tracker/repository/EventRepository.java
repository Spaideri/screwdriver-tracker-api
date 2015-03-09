package org.screwdriver.tracker.repository;

import org.screwdriver.tracker.entity.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRepository extends CrudRepository<Event, Long> {

    public List<Event> findByTrackerId(Long trackerId);

    public List<Event> findByTrackerTrackerCode(String trackerCode);

}
