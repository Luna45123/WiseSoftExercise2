package com.wiseSoft.Seminar.service;

import com.wiseSoft.Seminar.model.SeminarDay;
import com.wiseSoft.Seminar.model.SeminarTopic;
import com.wiseSoft.Seminar.util.IncrementDate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SeminarSchedulerService {
    public List<SeminarDay> scheduleSeminars(String startDate, List<SeminarTopic> topics) {
        List<SeminarDay> days = new ArrayList<>();
        List<SeminarDay> overDay = new ArrayList<>();
        IncrementDate incrementDate = new IncrementDate();
        AssignToSession assignToSession = new AssignToSession();
        LocalDate date = LocalDate.parse(startDate);
        int morningMaxTime = 180;
        int afternoonMaxTime = 180;
        int maxDayTime = 420;

        while (!topics.isEmpty()) {
            SeminarDay day = new SeminarDay(date);
            assignToSession.assignToSession(day.getMorningSession(), topics, morningMaxTime);
            assignToSession.assignToSession(day.getAfternoonSession(), topics, afternoonMaxTime);
            days.add(day);
            date = incrementDate.incrementDate(date);
        }
        // Add over day
        if ((days.getLast().getAfternoonSessionTime() + days.getLast().getMorningSessionTime()) < (morningMaxTime + afternoonMaxTime)) {
            overDay.add(days.getLast());
        }


        if (!overDay.isEmpty()) {
            List<SeminarTopic> allSessions = overDay.getFirst().getAllSession();
            overDay.getFirst().removeSession();
            for (SeminarDay seminarDay : days) {
                int overDaySize = allSessions.size();
                for (int j = 0; j < overDaySize; j++) {
                    if (!allSessions.isEmpty()) {
                        if (seminarDay.getMorningSessionTime() + allSessions.getFirst().getDuration() <= morningMaxTime && !allSessions.isEmpty()) {
                            seminarDay.addMorningSession(allSessions.removeFirst());
                        } else if (seminarDay.getAfternoonSessionTime() + allSessions.getFirst().getDuration() <= (maxDayTime - afternoonMaxTime) && !allSessions.isEmpty()) {
                            seminarDay.addAfternoonSession(allSessions.removeFirst());
                        }
                    }
                }
            }

        }
        days.removeIf(day -> day.getMorningSession().isEmpty() && day.getAfternoonSession().isEmpty());
        return days;
    }
}