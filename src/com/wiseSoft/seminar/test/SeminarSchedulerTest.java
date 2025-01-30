package com.wiseSoft.seminar.test;

import com.wiseSoft.seminar.model.SeminarDay;
import com.wiseSoft.seminar.model.SeminarTopic;
import com.wiseSoft.seminar.service.SeminarSchedulerService;
import com.wiseSoft.seminar.service.AssignToSessionService;
import com.wiseSoft.seminar.service.GetScheduleService;
import com.wiseSoft.seminar.util.FormatTime;
import com.wiseSoft.seminar.util.IncrementDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SeminarSchedulerTest {
    private SeminarSchedulerService seminarScheduler;
    private AssignToSessionService assignToSession;
    private FormatTime formatTime;
    private IncrementDate incrementDate;

    @BeforeEach
    void setUp() {
        seminarScheduler = new SeminarSchedulerService();
        assignToSession = new AssignToSessionService();
        formatTime = new FormatTime();
        incrementDate = new IncrementDate();
    }

    @Test
    void testSeminarTopicCreation() {
        SeminarTopic topic = new SeminarTopic("Test Seminar", 60);
        assertEquals("Test Seminar", topic.getTitle());
        assertEquals(60, topic.getDuration());
    }

    @Test
    void testSeminarDayCreation() {
        SeminarDay day = new SeminarDay(LocalDate.of(2022, 2, 25));
        assertNotNull(day.getMorningSession());
        assertNotNull(day.getAfternoonSession());
        assertEquals("25/02/2565", day.getFormattedDate());
    }

    @Test
    void testAssignToMorningSession() {
        List<SeminarTopic> session = new ArrayList<>();
        List<SeminarTopic> topics = new ArrayList<>();
        topics.add(new SeminarTopic("Test Topic 1", 60));
        topics.add(new SeminarTopic("Test Topic 2", 120));

        assignToSession.assign(session, topics, 180);

        assertEquals(2, session.size());
        assertEquals(0, topics.size());
    }

    @Test
    void testAssignToAfternoonSession() {
        List<SeminarTopic> session = new ArrayList<>();
        List<SeminarTopic> topics = new ArrayList<>();
        topics.add(new SeminarTopic("Test Topic 1", 90));
        topics.add(new SeminarTopic("Test Topic 2", 90));

        assignToSession.assign(session, topics, 180);

        assertEquals(2, session.size());
        assertEquals(0, topics.size());
    }

    @Test
    void testFormatTime() {
        LocalTime time = LocalTime.of(9, 0);
        assertEquals("09:00am", formatTime.formatTime(time));
    }

    @Test
    void testIncrementDate() {
        LocalDate date = LocalDate.of(2022, 2, 25);
        LocalDate newDate = incrementDate.incrementDate(date);
        assertEquals(LocalDate.of(2022, 2, 28), newDate); // Skipping weekend
    }

    @Test
    void testSeminarScheduler() {
        List<SeminarTopic> topics = new ArrayList<>();
        topics.add(new SeminarTopic("Topic 1", 60));
        topics.add(new SeminarTopic("Topic 2", 45));
        topics.add(new SeminarTopic("Topic 3", 30));
        topics.add(new SeminarTopic("Topic 4", 45));
        topics.add(new SeminarTopic("Topic 5", 60));
        topics.add(new SeminarTopic("Topic 6", 45));
        topics.add(new SeminarTopic("Topic 7", 30));
        topics.add(new SeminarTopic("Topic 8", 45));
        topics.add(new SeminarTopic("Topic 9", 30));
        topics.add(new SeminarTopic("Topic 10", 60));
        topics.add(new SeminarTopic("Topic 11", 60));
        topics.add(new SeminarTopic("Topic 12", 30));
        topics.add(new SeminarTopic("Topic 13", 30));
        topics.add(new SeminarTopic("Topic 14", 45));
        topics.add(new SeminarTopic("Topic 15", 60));
        topics.add(new SeminarTopic("Topic 16", 45));
        topics.add(new SeminarTopic("Topic 17", 30));
        topics.add(new SeminarTopic("Topic 18", 30));
        topics.add(new SeminarTopic("Topic 19", 5));

        List<SeminarDay> schedule = seminarScheduler.createScheduleSeminars("2022-02-25", topics);
        assertFalse(schedule.isEmpty());
        assertEquals(2, schedule.size());
    }

    @Test
    void testSchedulePrinting() {
        SeminarDay day = new SeminarDay(LocalDate.of(2022, 2, 25));
        day.getMorningSession().add(new SeminarTopic("Test Topic", 60));
        day.getAfternoonSession().add(new SeminarTopic("Test Topic 2", 90));

        GetScheduleService getSchedule = new GetScheduleService();
        String schedule = getSchedule.getSchedule(day.getMorningSession(), day.getAfternoonSession());

        assertTrue(schedule.contains("09:00am Test Topic 60min"));
        assertTrue(schedule.contains("01:00pm Test Topic 2 90min"));
    }
}

