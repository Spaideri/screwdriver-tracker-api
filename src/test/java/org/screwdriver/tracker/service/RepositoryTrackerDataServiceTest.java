package org.screwdriver.tracker.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.screwdriver.tracker.dto.TrackerDTO;
import org.screwdriver.tracker.entity.Event;
import org.screwdriver.tracker.entity.Tracker;
import org.screwdriver.tracker.repository.EventRepository;
import org.screwdriver.tracker.repository.TrackerRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

public class RepositoryTrackerDataServiceTest {

    private static final Long TRACKER_ID = 12L;

    @Mock
    private TrackerRepository trackerRepository;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private MapperService mapperService;

    @Mock
    private Event latestEvent;

    @Mock
    private Tracker tracker;

    @Mock
    private TrackerDTO trackerDTO;

    private RepositoryTrackerDataService dataService;

    @Before
    public void setup() {
        initMocks(this);
        recordMockLogic();
        dataService = new RepositoryTrackerDataService(trackerRepository, eventRepository, mapperService);
    }

    @Test
    public void shouldGetTrackersAndLatestEventAndMapThem() {
        List<TrackerDTO> dtos = dataService.getAll();

        verify(trackerRepository).findAll();
        verify(eventRepository).findTop1ByTrackerIdOrderByEventTimestampDesc(TRACKER_ID);
        verify(mapperService).mapTrackerToTrackerDTO(tracker, latestEvent);
        assertEquals(trackerDTO, dtos.get(0));
    }

    @Test
    public void shouldGetTrackerAndLatestEventAndMap() {
        TrackerDTO dto = dataService.getTracker(TRACKER_ID);

        verify(trackerRepository).findOne(TRACKER_ID);
        verify(eventRepository).findTop1ByTrackerIdOrderByEventTimestampDesc(TRACKER_ID);
        verify(mapperService).mapTrackerToTrackerDTO(tracker, latestEvent);
        assertEquals(trackerDTO, dto);
    }


    private void recordMockLogic() {
        when(trackerRepository.findAll()).thenReturn(Arrays.asList(tracker));
        when(trackerRepository.findOne(TRACKER_ID)).thenReturn(tracker);
        when(tracker.getId()).thenReturn(TRACKER_ID);
        when(eventRepository.findTop1ByTrackerIdOrderByEventTimestampDesc(TRACKER_ID)).thenReturn(latestEvent);
        when(mapperService.mapTrackerToTrackerDTO(tracker, latestEvent)).thenReturn(trackerDTO);
    }
}
