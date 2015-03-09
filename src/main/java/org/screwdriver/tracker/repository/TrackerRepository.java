package org.screwdriver.tracker.repository;

import org.screwdriver.tracker.entity.Tracker;
import org.springframework.data.repository.CrudRepository;

public interface TrackerRepository extends CrudRepository<Tracker, Long> {
}
