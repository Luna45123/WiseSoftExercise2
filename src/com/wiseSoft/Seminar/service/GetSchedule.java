package com.wiseSoft.Seminar.service;

import com.wiseSoft.Seminar.model.SeminarTopic;
import com.wiseSoft.Seminar.util.FormatTime;

import java.time.LocalTime;
import java.util.List;

public class GetSchedule {
    public String getSchedule(List<SeminarTopic> morningSession, List<SeminarTopic> afternoonSession) {
        StringBuilder schedule = new StringBuilder();
        FormatTime formatTime = new FormatTime();

        // Morning Session
        LocalTime time = LocalTime.of(9, 0);
        for (SeminarTopic topic : morningSession) {
            schedule.append(formatTime.formatTime(time)).append(" ").append(topic.getTitle()).append(" ").append(topic.getDuration()).append("min\n");
            time = time.plusMinutes(topic.getDuration());
        }
        schedule.append(formatTime.formatTime(time)).append(" Lunch\n");

        // Afternoon Session
        time = LocalTime.of(13, 0);
        for (SeminarTopic topic : afternoonSession) {
            schedule.append(formatTime.formatTime(time)).append(" ").append(topic.getTitle()).append(" ").append(topic.getDuration()).append("min\n");
            time = time.plusMinutes(topic.getDuration());
        }
        schedule.append(formatTime.formatTime(time)).append(" Networking Event");

        return schedule.toString();
    }
}
