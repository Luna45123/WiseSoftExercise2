package com.wiseSoft.Seminar.service;

import com.wiseSoft.Seminar.model.SeminarDay;

import java.util.List;

public class PrintSchedule {
    public String print(List<SeminarDay> schedule){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < schedule.size(); i++) {
            SeminarDay day = schedule.get(i);
            stringBuilder.append("Day ").append(i + 1).append(" - ").append(day.getFormattedDate()).append("\n");
            stringBuilder.append(day.getSchedule());
            stringBuilder.append("\n\n");
        }
        return stringBuilder.toString();
    }
}
