package com.wiseSoft.Seminar.model;

public class SeminarTopic {
    private final String title;
    private int duration;

    public SeminarTopic(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}