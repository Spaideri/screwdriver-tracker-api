package org.screwdriver.tracker.repository;

import org.screwdriver.tracker.entity.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRepository extends CrudRepository<Event, Long> {

    List<Event> findByTrackerId(Long trackerId);

    List<Event> findByTrackerTrackerCode(String trackerCode);

    Event findTop1ByTrackerIdOrderByEventTimestampDesc(Long trackerId);

}
