import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class SeminarDay {
    private final LocalDate date;
    private final List<SeminarTopic> morningSession = new ArrayList<>();
    private final List<SeminarTopic> afternoonSession = new ArrayList<>();

    public SeminarDay(LocalDate date) {
        this.date = date;
    }

    public List<SeminarTopic> getMorningSession() {
        return morningSession;
    }

    public List<SeminarTopic> getAfternoonSession() {
        return afternoonSession;
    }

    public String getFormattedDate() {
        int buddhistYear = date.getYear() + 543;
        return date.format(DateTimeFormatter.ofPattern("dd/MM/")) + buddhistYear;
    }

    public String getSchedule() {
        StringBuilder schedule = new StringBuilder();

        // Morning Session
        LocalTime time = LocalTime.of(9, 0);
        for (SeminarTopic topic : morningSession) {
            schedule.append(formatTime(time)).append(" ").append(topic.title).append(" ").append(topic.duration).append("min\n");
            time = time.plusMinutes(topic.duration);
        }
        schedule.append("12:00PM Lunch\n");

        // Afternoon Session
        time = LocalTime.of(13, 0);
        for (SeminarTopic topic : afternoonSession) {
            schedule.append(formatTime(time)).append(" ").append(topic.title).append(" ").append(topic.duration).append("min\n");
            time = time.plusMinutes(topic.duration);
        }
        schedule.append(formatTime(time)).append(" Networking Event");

        return schedule.toString();
    }

    private String formatTime(LocalTime time) {
        return time.format(DateTimeFormatter.ofPattern("hh:mma"));
    }
}
