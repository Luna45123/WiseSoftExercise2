package com.wiseSoft.seminar.service;

import com.wiseSoft.seminar.model.SeminarDay;
import com.wiseSoft.seminar.model.SeminarTopic;
import com.wiseSoft.seminar.util.IncrementDate;

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
            if (!days.getLast().getMorningSession().isEmpty() || !days.getLast().getAfternoonSession().isEmpty()){
                overDay.add(days.getLast());
            }
        }


        if (!overDay.isEmpty()) {
            List<SeminarTopic> allSessions = new ArrayList<>();
            allSessions.addAll(overDay.getFirst().getMorningSession());
            allSessions.addAll(overDay.getFirst().getAfternoonSession());
            overDay.getFirst().removeSession();

            for (SeminarDay seminarDay : days) {
                int morningSessionTime = seminarDay.getMorningSessionTime();
                int afternoonSessionTime = seminarDay.getAfternoonSessionTime();
                int allSessionsSize = allSessions.size();
                for (int j = 0; j < allSessionsSize; j++) {
                    if (morningSessionTime + allSessions.getFirst().getDuration() <= morningMaxTime && !allSessions.isEmpty()) {
                        seminarDay.addMorningSession(allSessions.removeFirst());
                        morningSessionTime = seminarDay.getMorningSessionTime();
                    } else if (afternoonSessionTime + allSessions.getFirst().getDuration() <= (maxDayTime - afternoonMaxTime) && !allSessions.isEmpty()) {
                        seminarDay.addAfternoonSession(allSessions.removeFirst());
                        afternoonSessionTime = seminarDay.getAfternoonSessionTime();
                    }
                }
            }

        }
        days.removeIf(day -> day.getMorningSession().isEmpty() && day.getAfternoonSession().isEmpty());
        return days;
    }
}