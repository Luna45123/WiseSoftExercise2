package com.wiseSoft.seminar;

import com.wiseSoft.seminar.model.SeminarDay;
import com.wiseSoft.seminar.model.SeminarTopic;
import com.wiseSoft.seminar.service.InputFileService;
import com.wiseSoft.seminar.service.ParseSeminarTopicsService;
import com.wiseSoft.seminar.service.PrintScheduleService;
import com.wiseSoft.seminar.service.SeminarSchedulerService;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            String path = "src/input.txt";
            List<String> input = InputFileService.readInputFile(path);
            if (input.isEmpty()) {
                System.err.println("Input file is empty or invalid.");
                return;
            }
            String startDate = input.getFirst();
            List<SeminarTopic> topics = ParseSeminarTopicsService.parseSeminarTopics(input);

            List<SeminarDay> schedule = SeminarSchedulerService.createScheduleSeminars(startDate, topics);

            System.out.print(PrintScheduleService.printSchedule(schedule));

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }

    }

}