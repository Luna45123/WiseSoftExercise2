package com.wiseSoft.seminar.model;

public class SeminarTopic {
    private final String title;
    private final int duration;

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
}