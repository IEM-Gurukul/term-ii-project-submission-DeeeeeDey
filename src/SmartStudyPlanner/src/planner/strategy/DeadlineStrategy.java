package planner.strategy;

import planner.model.Task;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DeadlineStrategy implements SchedulerStrategy {

    public List<Task> schedule(List<Task> tasks) {

        List<Task> sorted = new ArrayList<Task>(tasks);

        Collections.sort(sorted, new Comparator<Task>() {
            public int compare(Task t1, Task t2) {
                return t1.getDeadline().compareTo(t2.getDeadline());
            }
        });

        return sorted;
    }
}