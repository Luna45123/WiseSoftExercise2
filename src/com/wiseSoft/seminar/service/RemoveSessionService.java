package com.wiseSoft.seminar.service;

import com.wiseSoft.seminar.model.SeminarDay;

public class RemoveSessionService {
    public void  remove(SeminarDay seminarDay){
        seminarDay.getMorningSession().clear();
        seminarDay.getAfternoonSession().clear();
    }
}
