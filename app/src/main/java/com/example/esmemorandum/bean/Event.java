package com.example.esmemorandum.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event implements Serializable {
    private final static long serialVersionUID = 1L;
    private int id;

    private String event, location;
    private Date startDate, endDate;
    private Date startTime, endTime;
    private String remarks;
    private boolean isVibration, isRing;
    private SimpleDateFormat sdf_date, sdf_time;

    public Event() {
        sdf_date = new SimpleDateFormat("yyyy.MM.dd");
        sdf_time = new SimpleDateFormat("HH:mm");
        isVibration = false;
        isRing = false;
    }

    public Event(String event, String location, Date startDate, Date endDate, Date startTime, Date endTime, String remarks, boolean isVibration, boolean isRing) {
        sdf_date = new SimpleDateFormat("yyyy.MM.dd");
        sdf_time = new SimpleDateFormat("HH:mm");
        this.event = event;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.remarks = remarks;
        this.isVibration = isVibration;
        this.isRing = isRing;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SimpleDateFormat getSdf_date() {
        return sdf_date;
    }

    public SimpleDateFormat getSdf_time() {
        return sdf_time;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public boolean isVibration() {
        return isVibration;
    }

    public void setVibration(boolean vibration) {
        isVibration = vibration;
    }

    public boolean isRing() {
        return isRing;
    }

    public void setRing(boolean ring) {
        isRing = ring;
    }
}
