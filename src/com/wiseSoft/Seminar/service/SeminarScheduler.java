package com.wiseSoft.Seminar.service;

import com.wiseSoft.Seminar.model.SeminarDay;
import com.wiseSoft.Seminar.model.SeminarTopic;
import com.wiseSoft.Seminar.util.IncrementDate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SeminarScheduler {
    public List<SeminarDay> scheduleSeminars(String startDate, List<SeminarTopic> topics) {
        List<SeminarDay> days = new ArrayList<>();
        List<SeminarDay> overDay = new ArrayList<>();
        IncrementDate incrementDate = new IncrementDate();
        AssignToSession assignToSession = new AssignToSession();
        LocalDate date = LocalDate.parse(startDate);

        while (!topics.isEmpty()) {
            SeminarDay day = new SeminarDay(date);
            assignToSession.assignToSession(day.getMorningSession(), topics, 180);
            assignToSession.assignToSession(day.getAfternoonSession(), topics,180);
            days.add(day);
            date = incrementDate.incrementDate(date);
        }
        // Add over day
        if (days.getLast().getAfternoonSessionTime() + days.getLast().getMorningSessionTime() < 360) {
            overDay.add(days.getLast());
        }

        if (!overDay.isEmpty()) {
            for (SeminarDay seminarDay : days) {
                for (int j = 0; j < overDay.getFirst().getMorningSession().size(); j++) {
                    if (seminarDay.getAfternoonSessionTime() + overDay.getFirst().getMorningSession().getFirst().getDuration() <= 240) {
                        if (overDay.getFirst().getMorningSessionTime() + overDay.getFirst().getAfternoonSessionTime() < days.size() * 60) {
                            seminarDay.AddAfternoonSession(overDay.getFirst().getMorningSession().removeFirst());
                        }
                    }
                }
            }
        }
        days.removeIf(day -> day.getMorningSession().isEmpty() && day.getAfternoonSession().isEmpty());
        return days;
    }
}