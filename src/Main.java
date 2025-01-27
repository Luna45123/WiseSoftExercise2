import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("src/input.txt"));
        SeminarScheduler seminarScheduler = new SeminarScheduler();
        // Input Data
        String startDate = input.getFirst();
        List<SeminarTopic> topics = new ArrayList<>();
        for(int i = 1; i < input.size(); i++){
            String[] parts = input.get(i).split(" ");
            int duration = Integer.parseInt(parts[parts.length - 1].replace("min", ""));
            String title = String.join(" ", parts).replace(" " + parts[parts.length - 1], "");
            topics.add(new SeminarTopic(title,duration));
        }

        // Schedule Seminars
        List<SeminarDay> schedule = seminarScheduler.scheduleSeminars(startDate, new ArrayList<>(topics));

        // Print Schedule
        for (int i = 0; i < schedule.size(); i++) {
            SeminarDay day = schedule.get(i);
            System.out.println("Day " + (i + 1) + " - " + day.getFormattedDate() +" :");
            System.out.println(day.getSchedule());
            System.out.println();
        }
    }

}
