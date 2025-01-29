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
        int Freetime = 0;

        while (!topics.isEmpty()) {
            SeminarDay day = new SeminarDay(date);
            assignToSession.assignToSession(day.getMorningSession(), topics, morningMaxTime);
            assignToSession.assignToSession(day.getAfternoonSession(), topics, afternoonMaxTime);
            days.add(day);
            date = incrementDate.incrementDate(date);
        }
        // Add over day
        if (days.getLast().getAfternoonSessionTime() + days.getLast().getMorningSessionTime() < (morningMaxTime + afternoonMaxTime)) {
            overDay.add(days.getLast());
        }


        if (!overDay.isEmpty()) {
            for (int i = 0; i < days.size() - 1; i++) {
                int allDayTime = days.get(i).getMorningSessionTime() + days.get(i).getAfternoonSessionTime();
                Freetime += (maxDayTime - allDayTime);
            }
            if (overDay.getFirst().getMorningSessionTime() + overDay.getFirst().getAfternoonSessionTime() < Freetime) {
                List<SeminarTopic> allSessions = overDay.getFirst().getAllSession();
                overDay.getFirst().removeSession();
                for (SeminarDay seminarDay : days) {
                    int overDaySize = allSessions.size();
                    for (int j = 0; j < overDaySize;j++) {
                        if (!allSessions.isEmpty()){
                            if (seminarDay.getAfternoonSessionTime() + allSessions.getFirst().getDuration() <= (maxDayTime - afternoonMaxTime)) {
                                seminarDay.AddAfternoonSession(allSessions.removeFirst());
                            }
                        }
                    }
                }

            }

        }
        days.removeIf(day -> day.getMorningSession().isEmpty() && day.getAfternoonSession().isEmpty());
        return days;
    }
}