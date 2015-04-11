package org.screwdriver.tracker.repository;

import com.googlecode.flyway.core.Flyway;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.screwdriver.tracker.config.DataSourceConfig;
import org.screwdriver.tracker.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DataSourceConfig.class })
public class EventRepositoryTest {

    private static final int NUMBER_OF_EVENTS = 3;
    private static final String LATEST_TIMESTAMP = "2015-03-02T10:01:00.000+0000";

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private Flyway flyway;

    @Before
    public void setup() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void shouldFindEventsByTrackerId() {
        List<Event> trackerEvents = eventRepository.findByTrackerId(1L);
        assertEquals(NUMBER_OF_EVENTS, trackerEvents.size());
    }

    @Test
    public void shouldFindLatestEventByTrackerId() {
        Event event = eventRepository.findTop1ByTrackerIdOrderByEventTimestampDesc(1L);
        assertEquals(new DateTime(LATEST_TIMESTAMP).dayOfMonth(), new DateTime(event.getEventTimestamp()).dayOfMonth());
    }
}
