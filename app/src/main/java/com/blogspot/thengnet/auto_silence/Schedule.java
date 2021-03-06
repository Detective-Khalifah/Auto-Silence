package com.blogspot.thengnet.auto_silence;

public class Schedule {

    // TODO: Using {@link String} type for #startTime & #endTime during testing; re-factor to a
    //  suitable Date format later.
    private boolean isDay;
    private String title;
    private String description;
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;

    public Schedule (boolean isDay, String title, String description, String startDate,
                     String startTime, String endDate, String endTime) {
        this.isDay = isDay;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
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

    public String getStartDate () {
        return startDate;
    }

    public void setStartDate (String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime () {
        return startTime;
    }

    public void setStartTime (String startTime) {
        this.startTime = startTime;
    }

    public String getEndDate () {
        return endDate;
    }

    public void setEndDate (String endDate) {
        this.endDate = endDate;
    }

    public String getEndTime () {
        return endTime;
    }

    public void setEndTime (String endTime) {
        this.endTime = endTime;
    }
}