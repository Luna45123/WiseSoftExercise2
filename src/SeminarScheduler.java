import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SeminarScheduler {

    public List<SeminarDay> scheduleSeminars(String startDate, List<SeminarTopic> topics) {
        List<SeminarDay> days = new ArrayList<>();
        LocalDate date = LocalDate.parse(startDate);

        while (!topics.isEmpty()) {
            SeminarDay day = new SeminarDay(date);

            // Morning session: Fill until 180 minutes
            assignToSession(day.getMorningSession(), topics, 180);

            // Check if a next day exists and pass its topics
            List<SeminarTopic> nextDayTopics = topics.isEmpty() ? null : new ArrayList<>(topics);

            // Afternoon session: Fill until 240 minutes, ensuring it's at least 180 minutes
            assignToSessionWithCheck(day.getAfternoonSession(), topics, nextDayTopics, 240);

            // Add day to schedule
            days.add(day);

            // Increment date (skip weekends)
            date = incrementDate(date);
        }
        return days;
    }

    private static void assignToSession(List<SeminarTopic> session, List<SeminarTopic> topics, int maxDuration) {
        int currentDuration = 0;
        Iterator<SeminarTopic> iterator = topics.iterator();

        while (iterator.hasNext()) {
            SeminarTopic topic = iterator.next();
            if (currentDuration + topic.duration <= maxDuration) {
                session.add(topic);
                currentDuration += topic.duration;
                iterator.remove();
                if (currentDuration == maxDuration){
                    break;
                }
            }

        }
    }

    private static void assignToSessionWithCheck(List<SeminarTopic> session, List<SeminarTopic> topics, List<SeminarTopic> nextDayTopics, int maxDuration) {
        int currentDuration = session.stream().mapToInt(topic -> topic.duration).sum(); // Current session duration
        Iterator<SeminarTopic> iterator = topics.iterator();

        while (iterator.hasNext()) {
            SeminarTopic topic = iterator.next();
            if (currentDuration + topic.duration <= maxDuration) {
                session.add(topic);
                currentDuration += topic.duration;
                iterator.remove();
                if (currentDuration == maxDuration) {
                    break;
                }
            }
        }

        // If session duration < 180, attempt to borrow from next day
        if (currentDuration < 180 && nextDayTopics != null && !nextDayTopics.isEmpty()) {
            Iterator<SeminarTopic> nextDayIterator = nextDayTopics.iterator();
            while (nextDayIterator.hasNext() && currentDuration < 180) {
                SeminarTopic topic = nextDayIterator.next();
                session.add(topic);
                currentDuration += topic.duration;
                nextDayIterator.remove();
            }

            // Validate session duration
            if (currentDuration < 180) {
                throw new IllegalStateException("Unable to fill afternoon session to at least 180 minutes.");
            }
        }
    }


    private static LocalDate incrementDate(LocalDate date) {
        do {
            date = date.plusDays(1);
        } while (date.getDayOfWeek().toString().equals("SATURDAY") || date.getDayOfWeek().toString().equals("SUNDAY"));
        return date;
    }
}