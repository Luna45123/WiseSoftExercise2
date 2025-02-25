package com.wiseSoft.seminar.service;

import com.wiseSoft.seminar.model.SeminarDay;
import com.wiseSoft.seminar.model.SeminarTopic;
import com.wiseSoft.seminar.util.FormatTime;

import java.time.LocalTime;
import java.util.List;

public class PrintScheduleService {
    public static String printSchedule(List<SeminarDay> schedule) {
        StringBuilder stringBuilder = new StringBuilder();
        String newLine = System.lineSeparator();
        String dayText = "Day ";
        String space = " - ";
        String lunchText = " Lunch";
        String networkingText = " Networking Event";
        String minText = "min";

        for (int i = 0; i < schedule.size(); i++) {
            SeminarDay day = schedule.get(i);
            stringBuilder.append(dayText).append(i + 1).append(space).append(day.getFormattedDate()).append(newLine);

            // Morning Session
            LocalTime time = LocalTime.of(9, 0);
            for (SeminarTopic topic : day.getMorningSession()) {
                stringBuilder.append(FormatTime.formatTime(time)).append(" ").append(topic.title()).append(" ")
                        .append(topic.duration()).append(minText).append(newLine);
                time = time.plusMinutes(topic.duration());
            }
            stringBuilder.append(FormatTime.formatTime(time)).append(lunchText).append(newLine);

            // Afternoon Session
            time = LocalTime.of(13, 0);
            for (SeminarTopic topic : day.getAfternoonSession()) {
                stringBuilder.append(FormatTime.formatTime(time)).append(" ").append(topic.title()).append(" ")
                        .append(topic.duration()).append(minText).append(newLine);
                time = time.plusMinutes(topic.duration());
            }
            stringBuilder.append(FormatTime.formatTime(time)).append(networkingText).append(newLine).append(newLine);
        }
        return stringBuilder.toString();
    }

}
