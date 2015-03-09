package org.screwdriver.tracker.repository;

import org.screwdriver.tracker.entity.EventParameter;
import org.springframework.data.repository.CrudRepository;

public interface EventParameterRepository extends CrudRepository<EventParameter, Long> {
}
