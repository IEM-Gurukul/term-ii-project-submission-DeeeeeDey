package planner.service;

import planner.model.Task;
import java.util.List;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ReminderService {

    public void checkReminders(List<Task> tasks) {

        for (Task task : tasks) {

            long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), task.getDeadline());

            if (daysLeft < 0) {
                System.out.println("OVERDUE: " + task.getTitle());
            } else if (daysLeft <= 2) {
                System.out.println("DUE SOON: " + task.getTitle());
            }
        }
    }
}