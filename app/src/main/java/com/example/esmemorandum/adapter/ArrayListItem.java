package com.example.esmemorandum.adapter;

public class ArrayListItem {
    private String time, event, location;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayListItem(String time, String event, String location) {
        this.time = time;
        this.event = event;
        this.location = location;
    }
}
