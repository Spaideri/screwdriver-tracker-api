package org.screwdriver.tracker.service;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.screwdriver.tracker.dto.EventDTO;
import org.screwdriver.tracker.dto.TrackerDTO;
import org.screwdriver.tracker.entity.Event;
import org.screwdriver.tracker.entity.EventParameter;
import org.screwdriver.tracker.entity.Tracker;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MapperServiceTest {

    private static final Long EVENT_ID = 12L;
    private static final String EVENT_VERSION = "v1";
    public static final String EVENT_TIMESTAMP = "2012-01-08T20:57:30.123+0200";
    public static final String EVENT_SUBMIT_TIME = "2012-01-08T20:30:30.123+0200";
    private static final String PARAM1_KEY = "altitude";
    private static final String PARAM1_VALUE = "1430";
    private static final String PARAM2_KEY = "X-foobar";
    private static final String PARAM2_VALUE = "baz";
    private static final Long TRACKER_ID = 11L;
    private static final String TRACKER_CODE = "TrackerCode1";

    private MapperService mapperService = new MapperService();

    private Event event;

    private Tracker tracker;


    @Before
    public void setup() {
        buildEvent();
    }

    @Test
    public void shouldMapEventToEventDTO() {
        EventDTO dto = mapperService.mapEventToEventDTO(event);

        assertEquals(EVENT_ID, dto.getId());
        assertEquals(EVENT_VERSION, dto.getVersion());
        assertEquals(new DateTime(EVENT_SUBMIT_TIME).toString(), dto.getSubmitTime());
        assertEquals(new DateTime(EVENT_TIMESTAMP).toString(), dto.getEventTimestamp());
        assertEquals(PARAM1_VALUE, dto.getEventParameters().get(PARAM1_KEY));
        assertEquals(PARAM2_VALUE, dto.getEventParameters().get(PARAM2_KEY));
    }

    @Test
    public void shouldReturnNullWhenNullGiven() {
        EventDTO dto = mapperService.mapEventToEventDTO(null);
        assertNull(dto);
    }

    @Test
    public void shouldMapTrackerToTrackerDTO() {
        tracker = new Tracker.Builder()
                .id(TRACKER_ID)
                .trackerCode(TRACKER_CODE).build();

        TrackerDTO dto = mapperService.mapTrackerToTrackerDTO(tracker, event);
        assertEquals(TRACKER_ID, dto.getId());
        assertEquals(TRACKER_CODE, dto.getTrackerCode());
        assertEquals(EVENT_ID, dto.getLatestEvent().getId());
    }


    private void buildEvent() {
        Event.Builder builder = new Event.Builder()
                .id(EVENT_ID)
                .version(EVENT_VERSION)
                .eventTimestamp(new DateTime(EVENT_TIMESTAMP).toDate())
                .submitTime(new DateTime(EVENT_SUBMIT_TIME).toDate());

        ArrayList<EventParameter> eventParameters = new ArrayList<EventParameter>();
        EventParameter.Builder paramBuilder = new EventParameter.Builder();
        paramBuilder.key(PARAM1_KEY).value(PARAM1_VALUE);
        eventParameters.add(paramBuilder.build());
        paramBuilder.key(PARAM2_KEY).value(PARAM2_VALUE);
        eventParameters.add(paramBuilder.build());
        builder.eventParameters(eventParameters);
        event = builder.build();
    }
}
