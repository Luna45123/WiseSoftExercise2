package com.wiseSoft.seminar.service;

import com.wiseSoft.seminar.model.SeminarDay;

import java.util.List;

public class PrintSchedule {
    public String print(List<SeminarDay> schedule){
        String newLine = System.lineSeparator();
        String dayText = "Day ";
        String space = " - ";
        GetSchedule getSchedule = new GetSchedule();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < schedule.size(); i++) {
            SeminarDay day = schedule.get(i);
            stringBuilder.append(dayText).append(i + 1).append(space).append(day.getFormattedDate()).append(newLine);
            stringBuilder.append(getSchedule.getSchedule(day.getMorningSession(),day.getAfternoonSession()));
            stringBuilder.append(newLine).append(newLine);
        }
        return stringBuilder.toString();
    }
}
