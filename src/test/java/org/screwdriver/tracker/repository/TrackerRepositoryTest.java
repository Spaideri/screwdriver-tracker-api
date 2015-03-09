package org.screwdriver.tracker.repository;

import com.googlecode.flyway.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.screwdriver.tracker.config.DataSourceConfig;
import org.screwdriver.tracker.entity.Tracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DataSourceConfig.class })
public class TrackerRepositoryTest {

	private static final String TRACKER_CODE1 = "TEST_TRACKER_1";
	private static final int NUMBER_OF_TRACKERS = 2;

    @Autowired
	private TrackerRepository trackerRepository;

	@Autowired
	private Flyway flyway;

	@Before
	public void setup() {
		flyway.clean();
		flyway.migrate();
	}

	@Test
	public void shouldFindTrackerByTrackerCode() {
        Tracker tracker = trackerRepository.findByTrackerCode(TRACKER_CODE1);
        assertEquals(TRACKER_CODE1, tracker.getTrackerCode());
	}

	@Test
	public void shouldHaveTwoTrackers() {
		List<Tracker> trackers = new ArrayList<Tracker>();
		Iterable<Tracker> all = trackerRepository.findAll();
		Iterator<Tracker> iterator = all.iterator();
		while (iterator.hasNext()) {
            Tracker tracker = iterator.next();
			trackers.add(tracker);
		}
		assertEquals(NUMBER_OF_TRACKERS, trackers.size());
	}
}
