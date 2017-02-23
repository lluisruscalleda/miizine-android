package com.example.app.utils.analytics;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Event sent through Analytics
 */
public class Event {

  private final String type;
  private final String name;
  private final String value;
  private final Map<String, String> payload;
  private final Long timestamp;

  public Event(String type, String name) {
    this(type, name, null, new HashMap<String, String>(0));
  }

  public Event(String type, String name, String value) {
    this(type, name, value, new HashMap<String, String>(0));
  }

  public Event(String type, String name, Map<String, String> payload) {
    this(type, name, null, payload);
  }

  public Event(String type, String name, String value, Map<String, String> payload) {
    this(type, name, value, payload, new Date().getTime());
  }

  Event(String type, String name, String value, Map<String, String> payload, Long timestamp) {
    this.type = type;
    this.name = name;
    this.value = value;
    this.payload = payload;
    this.timestamp = timestamp;
  }

  public String getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public String getValue() {
    return value;
  }

  public Map<String, String> getPayload() {
    return payload;
  }

  public Long getTimestamp() {
    return timestamp;
  }

  @Override
  public String toString() {
    return "Event{" +
        "type='" + type + '\'' +
        ", name='" + name + '\'' +
        ", value='" + value + '\'' +
        ", payload=" + payload +
        ", timestamp=" + timestamp +
        '}';
  }
}
