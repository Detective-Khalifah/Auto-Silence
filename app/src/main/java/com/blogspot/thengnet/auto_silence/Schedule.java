package com.blogspot.thengnet.auto_silence;

import java.sql.Date;

public class Schedule {

    private boolean isDay;
    private String title;
    private String description;
    private Date startTime;
    private Date endTime;

    public Schedule (boolean isDay, String title, String description, Date startTime, Date endTime) {
        this.isDay = isDay;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean isDay () {
        return isDay;
    }

    public void setDay (boolean day) {
        isDay = day;
    }

    public String getTitle () {
        return title;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public String getDescription () {
        return description;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    public Date getStartTime () {
        return startTime;
    }

    public void setStartTime (Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime () {
        return endTime;
    }

    public void setEndTime (Date endTime) {
        this.endTime = endTime;
    }
}