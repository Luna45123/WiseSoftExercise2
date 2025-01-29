package com.wiseSoft.Seminar.model;

import com.wiseSoft.Seminar.service.GetSchedule;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SeminarDay {
    private final LocalDate date;
    private final List<SeminarTopic> morningSession = new ArrayList<>();
    private final List<SeminarTopic> afternoonSession = new ArrayList<>();
    private final GetSchedule getSchedule = new GetSchedule();
    String datePattern = "dd/MM/";

    public SeminarDay(LocalDate date) {
        this.date = date;
    }

    public List<SeminarTopic> getMorningSession() {
        return morningSession;
    }

    public List<SeminarTopic> getAfternoonSession() {
        return afternoonSession;
    }

    public String getFormattedDate() {
        int buddhistYear = date.getYear() + 543;
        return date.format(DateTimeFormatter.ofPattern(datePattern)) + buddhistYear;
    }

    public String getSchedule() {
        return getSchedule.getSchedule(morningSession,afternoonSession);
    }

    public int getAfternoonSessionTime(){
        return afternoonSession.stream().mapToInt(SeminarTopic::getDuration).sum();
    }

    public int getMorningSessionTime(){
        return morningSession.stream().mapToInt(SeminarTopic::getDuration).sum();
    }

    public void AddAfternoonSession(SeminarTopic seminarTopic){
        afternoonSession.add(seminarTopic);
    }

    public List<SeminarTopic> getAllSession(){
        List<SeminarTopic> allSessions = new ArrayList<>();
        allSessions.addAll(morningSession);
        allSessions.addAll(afternoonSession);
        return allSessions;
    }

    public void removeSession(){
        morningSession.clear();
        afternoonSession.clear();
    }
}
