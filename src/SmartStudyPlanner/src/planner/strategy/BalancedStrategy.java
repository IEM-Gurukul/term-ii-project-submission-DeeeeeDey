package planner.strategy;

import planner.model.Task;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BalancedStrategy implements SchedulerStrategy {

    public List<Task> schedule(List<Task> tasks) {

        List<Task> sorted = new ArrayList<Task>(tasks);

        Collections.sort(sorted, new Comparator<Task>() {
            public int compare(Task t1, Task t2) {
                double score1 = calculateScore(t1);
                double score2 = calculateScore(t2);
                return Double.compare(score2, score1);
            }
        });

        return sorted;
    }

    private double calculateScore(Task task) {

        long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), task.getDeadline());

        if (daysLeft <= 0) {
            daysLeft = 1;
        }

        return (task.getDifficulty() * 2.0) + task.getEstimatedHours() + (10.0 / daysLeft);
    }
}