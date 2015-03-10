package org.screwdriver.tracker.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.screwdriver.tracker.entity.Tracker;
import org.screwdriver.tracker.exception.BadRequestException;
import org.screwdriver.tracker.exception.UnauthorizedException;
import org.screwdriver.tracker.repository.EventParameterRepository;
import org.screwdriver.tracker.repository.EventRepository;
import org.screwdriver.tracker.repository.TrackerRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

public class RepositoryEventDataServiceTest {

    public static final String KEY_VERSION = "version";
    public static final String VALUE_VERSION = "1";
    public static final String KEY_TRACKER_CODE = "tracker_code";
    public static final String VALUE_TRACKER_CODE = "TEST_TRACKER_3";
    public static final String KEY_NONCE = "nonce";
    public static final String VALUE_NONCE = "XYZ123";
    public static final String KEY_TIME = "time";
    public static final String VALUE_TIME = "2012-01-08T20:57:30.123+0200";
    public static final String VALUE_INVALID_TIME = "2012-0-08T20:57:30.123+0200";
    public static final String KEY_X_VARIABLE = "X-foobar";
    public static final String VALUE_X_VARIABLE = "foobarvalue";
    public static final String KEY_MAC = "mac";
    public static final String VALUE_INVALID_MAC = "xxyyyzz";

    public static final String VALUE_SEPARATOR = ":";
    public static final String PARAMETER_SEPARATOR = "|";


    @Mock
    private TrackerRepository trackerRepository;

    @Mock
    private EventRepository eventRepository;

    @Mock
    private EventParameterRepository eventParameterRepository;

    private MapperService mapperService = new MapperService();

    private final static String MAC_SECRET = "MACSECRET123";

    private RepositoryEventDataService eventDataService;

    private Map<String, String> eventData;

    @Before
    public void setup() {
        initMocks(this);
        eventDataService = new RepositoryEventDataService(trackerRepository, eventRepository, eventParameterRepository, mapperService);
        eventDataService.setMacSecret(MAC_SECRET);
        eventData = createEventData();
        when(trackerRepository.findByTrackerCode(VALUE_TRACKER_CODE)).thenReturn(new Tracker());
    }

    @Test(expected = UnauthorizedException.class)
    public void shouldThrowUnauthorizedOnInvalidMac() {
        eventData.put(KEY_MAC, VALUE_INVALID_MAC);
        eventDataService.saveEvent(eventData);
    }

    @Test(expected = BadRequestException.class)
    public void shouldThrowExceptionOnMissingMac() {
        eventDataService.saveEvent(eventData);
    }

    @Test(expected = BadRequestException.class)
    public void shouldThrowBadRequestExceptionOnMissingVersion() {
        eventData.remove(KEY_VERSION);
        eventData.put(KEY_MAC, getMacForEventData(eventData));
        eventDataService.saveEvent(eventData);
    }

    @Test(expected = BadRequestException.class)
    public void shouldThrowBadRequestExceptionOnUnmatchingTrackerCode() {
        when(trackerRepository.findByTrackerCode(VALUE_TRACKER_CODE)).thenReturn(null);
        eventData.put(KEY_MAC, getMacForEventData(eventData));
        eventDataService.saveEvent(eventData);
    }

    @Test(expected = BadRequestException.class)
    public void shouldThrowBadRequestOnInvalidTimestmp() {
        eventData.put(KEY_TIME, VALUE_INVALID_TIME);
        eventData.put(KEY_MAC, getMacForEventData(eventData));
        eventDataService.saveEvent(eventData);
    }

    public static String getMacForEventData(Map<String, String> eventData) {
        Map<String, String> sortedMap = new TreeMap<>(eventData);
        StringBuffer sb = new StringBuffer();
        for(Map.Entry<String, String> entry : sortedMap.entrySet()) {
            sb.append(entry.getKey() + VALUE_SEPARATOR + entry.getValue() + PARAMETER_SEPARATOR);
        }
        sb.append(MAC_SECRET);
        return DigestUtils.sha1Hex(sb.toString());
    }

    public static Map<String, String> createEventData() {
        Map<String, String> data = new HashMap<String, String>();
        data.put(KEY_TRACKER_CODE, VALUE_TRACKER_CODE);
        data.put(KEY_VERSION, VALUE_VERSION);
        data.put(KEY_NONCE, VALUE_NONCE);
        data.put(KEY_X_VARIABLE, VALUE_X_VARIABLE);
        data.put(KEY_TIME, VALUE_TIME);
        return data;
    }
}
