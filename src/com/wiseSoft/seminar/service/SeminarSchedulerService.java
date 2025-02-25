package com.wiseSoft.seminar.service;

import com.wiseSoft.seminar.model.SeminarDay;
import com.wiseSoft.seminar.model.SeminarTopic;
import com.wiseSoft.seminar.util.IncrementDate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SeminarSchedulerService {
    public static List<SeminarDay> createScheduleSeminars(String startDate, List<SeminarTopic> topics) {
        List<SeminarDay> days = new ArrayList<>();
        List<SeminarDay> overDay = new ArrayList<>();
        LocalDate date = LocalDate.parse(startDate);
        int morningMaxTime = 180;
        int afternoonMaxTime = 180;
        int maxDayTime = 420;

        while (!topics.isEmpty()) {
            SeminarDay day = new SeminarDay(date);
            AssignToSessionService.assign(day.getMorningSession(), topics, morningMaxTime);
            AssignToSessionService.assign(day.getAfternoonSession(), topics, afternoonMaxTime);
            days.add(day);
            date = IncrementDate.incrementDate(date);
        }
        // Add over day if session time < 4.00pm
        if (!days.getLast().getMorningSession().isEmpty() || !days.getLast().getAfternoonSession().isEmpty()){
            if ((days.getLast().getAfternoonSessionTime() + days.getLast().getMorningSessionTime()) < (morningMaxTime + afternoonMaxTime)) {
                overDay.add(days.getLast());
            }
        }



        if (!overDay.isEmpty()) {
            List<SeminarTopic> allSessions = new ArrayList<>();
            allSessions.addAll(overDay.getFirst().getMorningSession());
            allSessions.addAll(overDay.getFirst().getAfternoonSession());
            RemoveSessionService.remove(overDay.getFirst());

            for (SeminarDay seminarDay : days) {
                int morningSessionTime = seminarDay.getMorningSessionTime();
                int afternoonSessionTime = seminarDay.getAfternoonSessionTime();
                int allSessionsSize = allSessions.size();
                for (int j = 0; j < allSessionsSize; j++) {
                    if (morningSessionTime + allSessions.getFirst().duration() <= morningMaxTime && !allSessions.isEmpty()) {
                        seminarDay.addMorningSession(allSessions.removeFirst());
                        morningSessionTime = seminarDay.getMorningSessionTime();
                    } else if (afternoonSessionTime + allSessions.getFirst().duration() <= (maxDayTime - afternoonMaxTime) && !allSessions.isEmpty()) {
                        seminarDay.addAfternoonSession(allSessions.removeFirst());
                        afternoonSessionTime = seminarDay.getAfternoonSessionTime();
                    }
                    //break if day full
                    if (morningSessionTime + afternoonSessionTime >= maxDayTime || allSessions.isEmpty()){
                        break;
                    }
                }
            }

        }
        days.removeIf(day -> day.getMorningSession().isEmpty() && day.getAfternoonSession().isEmpty());
        return days;
    }
}