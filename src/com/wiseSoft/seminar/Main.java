package com.wiseSoft.seminar;

import com.wiseSoft.seminar.model.SeminarDay;
import com.wiseSoft.seminar.model.SeminarTopic;
import com.wiseSoft.seminar.service.InputFileService;
import com.wiseSoft.seminar.service.ParseSeminarTopicsService;
import com.wiseSoft.seminar.service.PrintScheduleService;
import com.wiseSoft.seminar.service.SeminarSchedulerService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Enter File Path: ");

        try {
            String path = "src/input.txt";

            InputFileService fileInputService = new InputFileService();

            List<String> input = fileInputService.readInputFile(path);
            if (input.isEmpty()) {
                System.err.println("Input file is empty or invalid.");
                return;
            }

            SeminarSchedulerService seminarScheduler = new SeminarSchedulerService();
            PrintScheduleService printSchedule = new PrintScheduleService();
            ParseSeminarTopicsService parseSeminarTopics = new ParseSeminarTopicsService();

            String startDate = input.getFirst();
            List<SeminarTopic> topics = parseSeminarTopics.parseSeminarTopics(input);

            List<SeminarDay> schedule = seminarScheduler.createScheduleSeminars(startDate, topics);

            System.out.print(printSchedule.print(schedule));

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }

    }

}
