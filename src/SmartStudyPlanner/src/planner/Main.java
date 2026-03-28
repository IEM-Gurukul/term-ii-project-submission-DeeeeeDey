package planner;

import planner.repository.TaskRepository;
import planner.service.PlannerService;
import planner.service.ReminderService;
import planner.ui.ConsoleUI;

public class Main {

    public static void main(String[] args) {

        PlannerService plannerService = new PlannerService();
        ReminderService reminderService = new ReminderService();
        TaskRepository repository = new TaskRepository();

        plannerService.getAllTasks().addAll(repository.loadTasks());

        ConsoleUI ui = new ConsoleUI(plannerService, reminderService, repository);

        ui.start();
    }
}