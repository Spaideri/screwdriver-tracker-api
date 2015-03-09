package org.screwdriver.tracker.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.screwdriver.tracker.entity.Event;
import org.screwdriver.tracker.entity.EventParameter;
import org.screwdriver.tracker.entity.Tracker;
import org.screwdriver.tracker.exception.BadRequestException;
import org.screwdriver.tracker.exception.UnauthorizedException;
import org.screwdriver.tracker.repository.EventParameterRepository;
import org.screwdriver.tracker.repository.EventRepository;
import org.screwdriver.tracker.repository.TrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class RepositoryEventDataService implements IEventDataService {

    private static final String VALUE_SEPARATOR = ":";
    private static final String PARAMETER_SEPARATOR = "|";
    private static final String MAC_KEY = "mac";
    private static final String TRACKER_CODE_KEY = "tracker_code";
    private static final String VERSION_KEY = "version";
    private static final String EVENT_TIMESTAMP_KEY = "time";


    private String macSecret;

    private TrackerRepository trackerRepository;

    private EventRepository eventRepository;

    private EventParameterRepository eventParameterRepository;

    @Autowired
    public RepositoryEventDataService(TrackerRepository trackerRepository, EventRepository eventRepository, EventParameterRepository eventParameterRepository) {
        this.trackerRepository = trackerRepository;
        this.eventRepository = eventRepository;
        this.eventParameterRepository = eventParameterRepository;
    }

    @Value("${tracker.macSecret}")
    public void setMacSecret(String macSecret) {
        this.macSecret = macSecret;
    }

    @Override
    @Transactional
    public void saveEvent(Map<String, String> eventData) {
        validateMac(eventData);
        Tracker tracker = getTracker(eventData);
        Event event = new Event();
        event.setTracker(tracker);
        event.setVersion(getVersion(eventData));
        event.setEventTimestamp(getEventTimestamp(eventData));
        Event savedEvent = eventRepository.save(event);
        saveEventParameters(savedEvent, eventData);
    }

    private void saveEventParameters(Event event, Map<String, String> eventData) {
        List<EventParameter> eventParameters = new ArrayList<EventParameter>();
        for(Map.Entry<String, String> entry : eventData.entrySet()) {
            EventParameter eventParameter = new EventParameter(event, entry.getKey(), entry.getValue());
            eventParameters.add(eventParameterRepository.save(eventParameter));
        }
        event.setEventParameters(eventParameters);
    }

    private Tracker getTracker(Map<String, String> eventData) {
        Tracker tracker = trackerRepository.findByTrackerCode(eventData.get(TRACKER_CODE_KEY));
        if(null == tracker) {
            throw new BadRequestException("No tracker can be found with given tracker code");
        }
        eventData.remove(TRACKER_CODE_KEY);
        return tracker;
    }

    private String getVersion(Map<String, String> eventData) {
        String version = eventData.get(VERSION_KEY);
        if(null == version) {
            throw new BadRequestException("Missing mandatory version field");
        }
        eventData.remove(VERSION_KEY);
        return version;
    }

    private Date getEventTimestamp(Map<String, String> eventData) {
        Date timestamp = null;
        String eventTimestamp = eventData.get(EVENT_TIMESTAMP_KEY);
        if(null != eventTimestamp) {
            eventData.remove(EVENT_TIMESTAMP_KEY);
            try {
                timestamp = new DateTime(eventTimestamp).toDate();
            } catch (Exception e) {
                throw new BadRequestException(e.getMessage());
            }

        }
        return timestamp;
    }

    private void validateMac(Map<String, String> eventData) {
        String eventDataMac = eventData.get(MAC_KEY);
        if(null == eventDataMac) {
            throw new BadRequestException("Missing mac");
        }
        eventData.remove(MAC_KEY);
        if(!eventDataMac.equals(getMacForEventData(eventData))) {
            throw new UnauthorizedException("Invalid mac");
        }
    }

    private String getMacForEventData(Map<String, String> eventData) {
        Map<String, String> sortedMap = new TreeMap<>(eventData);
        StringBuffer sb = new StringBuffer();
        for(Map.Entry<String, String> entry : sortedMap.entrySet()) {
            sb.append(entry.getKey() + VALUE_SEPARATOR + entry.getValue() + PARAMETER_SEPARATOR);
        }
        sb.append(macSecret);
        return DigestUtils.sha1Hex(sb.toString());
    }
}
