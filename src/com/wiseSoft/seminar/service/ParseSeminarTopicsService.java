package com.wiseSoft.seminar.service;

import com.wiseSoft.seminar.model.SeminarTopic;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseSeminarTopicsService {
    private static final Pattern DURATION_PATTERN = Pattern.compile("\\s(\\d+)min$");
    public static List<SeminarTopic> parseSeminarTopics(List<String> input) {
        List<SeminarTopic> topics = new ArrayList<>();

        for (int i = 1; i < input.size(); i++) {
            String line = input.get(i).trim();
            if (!line.isEmpty()) {
                Matcher matcher = DURATION_PATTERN.matcher(line);
                if (matcher.find()) {
                    int duration = Integer.parseInt(matcher.group(1));
                    StringBuilder titleBuilder = new StringBuilder(line);
                    titleBuilder.setLength(titleBuilder.length() - matcher.group(0).length());
                    String title = titleBuilder.toString().trim();

                    topics.add(new SeminarTopic(title, duration));
                }
            }
        }
        return topics;
    }
}
