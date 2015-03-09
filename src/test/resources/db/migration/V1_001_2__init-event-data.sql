INSERT INTO event (tracker_id, version, submitTime, eventTimestamp) VALUES (
  (SELECT id FROM tracker WHERE trackerCode = 'TEST_TRACKER_1'), '1', NOW(), TIMESTAMP '2015-03-01 12:00:00');
INSERT INTO eventParameter(event_id, key, value) VALUES ((SELECT MAX(id) FROM event), 'latitude', '1.1,N');
INSERT INTO eventParameter(event_id, key, value) VALUES ((SELECT MAX(id) FROM event), 'longitude', '1.2,W');

INSERT INTO event (tracker_id, version, submitTime, eventTimestamp) VALUES (
  (SELECT id FROM tracker WHERE trackerCode = 'TEST_TRACKER_1'), '1', NOW(), TIMESTAMP '2015-03-01 12:01:00');
INSERT INTO eventParameter(event_id, key, value) VALUES ((SELECT MAX(id) FROM event), 'latitude', '2.1,N');
INSERT INTO eventParameter(event_id, key, value) VALUES ((SELECT MAX(id) FROM event), 'longitude', '2.2,W');

INSERT INTO event (tracker_id, version, submitTime, eventTimestamp) VALUES (
  (SELECT id FROM tracker WHERE trackerCode = 'TEST_TRACKER_2'), '1', NOW(), TIMESTAMP '2015-03-01 12:01:00');
INSERT INTO eventParameter(event_id, key, value) VALUES ((SELECT MAX(id) FROM event), 'latitude', '2.1,N');
INSERT INTO eventParameter(event_id, key, value) VALUES ((SELECT MAX(id) FROM event), 'longitude', '2.2,W');