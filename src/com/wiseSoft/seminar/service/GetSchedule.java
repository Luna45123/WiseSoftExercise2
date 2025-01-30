package com.wiseSoft.seminar.service;

import com.wiseSoft.seminar.model.SeminarTopic;
import com.wiseSoft.seminar.util.FormatTime;

import java.time.LocalTime;
import java.util.List;

public class GetSchedule {
    public String getSchedule(List<SeminarTopic> morningSession, List<SeminarTopic> afternoonSession) {
        StringBuilder schedule = new StringBuilder();
        FormatTime formatTime = new FormatTime();
        String newLine = System.lineSeparator();
        String lunchText = " Lunch";
        String NetworkingText = " Networking Event";
        String minText = "min";
        String space = " ";

        // Morning Session
        LocalTime time = LocalTime.of(9, 0);
        for (SeminarTopic topic : morningSession) {
            schedule.append(formatTime.formatTime(time)).append(space).append(topic.getTitle()).append(space).append(topic.getDuration()).append(minText).append(newLine);
            time = time.plusMinutes(topic.getDuration());
        }
        schedule.append(formatTime.formatTime(time)).append(lunchText).append(newLine);

        // Afternoon Session
        time = LocalTime.of(13, 0);
        for (SeminarTopic topic : afternoonSession) {
            schedule.append(formatTime.formatTime(time)).append(space).append(topic.getTitle()).append(space).append(topic.getDuration()).append(minText).append(newLine);
            time = time.plusMinutes(topic.getDuration());
        }
        schedule.append(formatTime.formatTime(time)).append(NetworkingText);

        return schedule.toString();
    }
}
