package com.wiseSoft.seminar.service;

import com.wiseSoft.seminar.model.SeminarTopic;

import java.util.Iterator;
import java.util.List;

public class AssignToSessionService {
    public void assign(List<SeminarTopic> session, List<SeminarTopic> topics, int maxDuration) {
        int currentDuration = session.stream().mapToInt(SeminarTopic::duration).sum();
        Iterator<SeminarTopic> iterator = topics.iterator();

        while (iterator.hasNext()) {
            SeminarTopic topic = iterator.next();
            if (currentDuration + topic.duration() <= maxDuration) {
                session.add(topic);
                currentDuration += topic.duration();
                iterator.remove();
                if (currentDuration >= maxDuration){
                    break;
                }
            }
        }
    }
}
