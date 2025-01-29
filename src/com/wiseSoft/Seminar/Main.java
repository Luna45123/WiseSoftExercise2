package com.wiseSoft.Seminar;

import com.wiseSoft.Seminar.model.SeminarDay;
import com.wiseSoft.Seminar.model.SeminarTopic;
import com.wiseSoft.Seminar.service.PrintSchedule;
import com.wiseSoft.Seminar.service.SeminarScheduler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/input.txt"));
        SeminarScheduler seminarScheduler = new SeminarScheduler();
        PrintSchedule printSchedule = new PrintSchedule();
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
