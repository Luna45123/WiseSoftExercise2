package com.wiseSoft.seminar.service;

import com.wiseSoft.seminar.model.SeminarTopic;

import java.util.Iterator;
import java.util.List;

public class AssignToSessionService {
    public void assign(List<SeminarTopic> session, List<SeminarTopic> topics, int maxDuration) {
        int currentDuration = session.stream().mapToInt(SeminarTopic::getDuration).sum();
        Iterator<SeminarTopic> iterator = topics.iterator();

        while (iterator.hasNext()) {
            SeminarTopic topic = iterator.next();
            if (currentDuration + topic.getDuration() <= maxDuration) {
                session.add(topic);
                currentDuration += topic.getDuration();
                iterator.remove();
                if (currentDuration >= maxDuration){
                    break;
                }
            }
        }
    }
}
