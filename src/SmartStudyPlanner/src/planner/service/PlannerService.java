package planner.service;

import planner.model.Task;
import planner.strategy.SchedulerStrategy;
import java.util.List;
import java.util.ArrayList;

public class PlannerService {

    private List<Task> tasks;
    private SchedulerStrategy strategy;

    public PlannerService() {
        tasks = new ArrayList<Task>();
    }

    public void setStrategy(SchedulerStrategy strategy) {
        this.strategy = strategy;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public boolean removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            return true;
        }
        return false;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public List<Task> getScheduledTasks() {
        if (strategy == null) {
            return tasks;
        }
        return strategy.schedule(tasks);
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}