package planner.strategy;

import planner.model.Task;
import java.util.List;

public interface SchedulerStrategy {

    List<Task> schedule(List<Task> tasks);

}