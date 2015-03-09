package org.screwdriver.tracker.repository;

import com.googlecode.flyway.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.screwdriver.tracker.config.DataSourceConfig;
import org.screwdriver.tracker.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DataSourceConfig.class })
public class EventRepositoryTest {
    
    private static final int NUMBER_OF_EVENTS = 3;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private Flyway flyway;

    @Before
    public void setup() {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void shouldFindEventsByTrackerId() {
        List<Event> trackerEvents = eventRepository.findByTrackerId(1L);
        assertEquals(2, trackerEvents.size());
    }

    @Test
    public void shouldHaveTwoEvents() {
        List<Event> events = new ArrayList<Event>();
        Iterable<Event> all = eventRepository.findAll();
        Iterator<Event> iterator = all.iterator();
        while (iterator.hasNext()) {
            Event event = iterator.next();
            events.add(event);
        }
        assertEquals(NUMBER_OF_EVENTS, events.size());
    }
}
