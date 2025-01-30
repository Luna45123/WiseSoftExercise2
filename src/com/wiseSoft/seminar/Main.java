package com.wiseSoft.seminar;

import com.wiseSoft.seminar.model.SeminarDay;
import com.wiseSoft.seminar.model.SeminarTopic;
import com.wiseSoft.seminar.service.PrintScheduleService;
import com.wiseSoft.seminar.service.SeminarSchedulerService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.print("Enter File Path: ");
        Scanner sc = new Scanner(System.in);
        String path = sc.next();
        List<String> input = Files.readAllLines(Path.of(path));
        SeminarSchedulerService seminarScheduler = new SeminarSchedulerService();
        PrintScheduleService printSchedule = new PrintScheduleService();
        // Input Data
        String startDate = input.getFirst();
        List<SeminarTopic> topics = new ArrayList<>();
        for(int i = 1; i < input.size(); i++){
            String line = input.get(i).trim();
            if (!line.isEmpty()) {
                String[] parts = line.split(" ");
                int duration = Integer.parseInt(parts[parts.length - 1].replace("min", ""));
                String title = String.join(" ", parts).replace(" " + parts[parts.length - 1], "");
                topics.add(new SeminarTopic(title, duration));
            }

        }
        // Schedule Seminars
        List<SeminarDay> schedule = seminarScheduler.scheduleSeminars(startDate, new ArrayList<>(topics));

        // Print Schedule
        System.out.print(printSchedule.print(schedule));
    }

}
