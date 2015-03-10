package org.screwdriver.tracker.service;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.screwdriver.tracker.dto.EventDTO;
import org.screwdriver.tracker.entity.Event;
import org.screwdriver.tracker.entity.EventParameter;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class MapperServiceTest {

    private static final Long EVENT_ID = 12L;
    private static final String EVENT_VERSION = "v1";
    public static final String EVENT_TIMESTAMP = "2012-01-08T20:57:30.123+0200";
    public static final String EVENT_SUBMIT_TIME = "2012-01-08T20:30:30.123+0200";
    private static final String PARAM1_KEY = "altitude";
    private static final String PARAM1_VALUE = "1430";
    private static final String PARAM2_KEY = "X-foobar";
    private static final String PARAM2_VALUE = "baz";

    private MapperService mapperService = new MapperService();

    private Event event;


    @Before
    public void setup() {
        buildEvent();
    }

    @Test
    public void shouldMapEventToEventDTO() {
        EventDTO dto = mapperService.mapEventToEventDTO(event);

        assertEquals(EVENT_ID, dto.getId());
        assertEquals(EVENT_VERSION, dto.getVersion());
        assertEquals(new DateTime(EVENT_SUBMIT_TIME), new DateTime(dto.getSubmitTime()));
        assertEquals(new DateTime(EVENT_TIMESTAMP), new DateTime(dto.getEventTimestamp()));
        assertEquals(PARAM1_VALUE, dto.getEventParameters().get(PARAM1_KEY));
        assertEquals(PARAM2_VALUE, dto.getEventParameters().get(PARAM2_KEY));
    }

    private void buildEvent() {
        Event.Builder builder = new Event.Builder();
        builder.id(EVENT_ID);
        builder.version(EVENT_VERSION);
        builder.eventTimestamp(new DateTime(EVENT_TIMESTAMP).toDate());
        builder.submitTime(new DateTime(EVENT_SUBMIT_TIME).toDate());

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
