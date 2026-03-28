package planner.ui;

import planner.model.Task;
import planner.model.AssignmentTask;
import planner.model.ExamTask;
import planner.model.ReadingTask;
import planner.service.PlannerService;
import planner.service.ReminderService;
import planner.repository.TaskRepository;
import planner.strategy.DeadlineStrategy;
import planner.strategy.DifficultyStrategy;
import planner.strategy.BalancedStrategy;
import planner.exception.InvalidTaskException;

import java.util.Scanner;
import java.util.List;
import java.time.LocalDate;

public class ConsoleUI {

    private PlannerService plannerService;
    private ReminderService reminderService;
    private TaskRepository repository;
    private Scanner sc;

    public ConsoleUI(PlannerService plannerService, ReminderService reminderService, TaskRepository repository) {
        this.plannerService = plannerService;
        this.reminderService = reminderService;
        this.repository = repository;
        this.sc = new Scanner(System.in);
    }

    public void start() {

        while (true) {

            System.out.println("\n1. Add Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. View Scheduled Tasks");
            System.out.println("4. Change Strategy");
            System.out.println("5. Remove Task");
            System.out.println("6. Check Reminders");
            System.out.println("7. Save and Exit");

            int choice = Integer.parseInt(sc.nextLine());

            try {

                if (choice == 1) {
                    addTask();
                } else if (choice == 2) {
                    viewAllTasks();
                } else if (choice == 3) {
                    viewScheduledTasks();
                } else if (choice == 4) {
                    changeStrategy();
                } else if (choice == 5) {
                    removeTask();
                } else if (choice == 6) {
                    reminderService.checkReminders(plannerService.getAllTasks());
                } else if (choice == 7) {
                    repository.saveTasks(plannerService.getAllTasks());
                    break;
                }

            } catch (InvalidTaskException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void addTask() throws InvalidTaskException {

        System.out.println("1. Assignment  2. Exam  3. Reading");
        int type = Integer.parseInt(sc.nextLine());

        System.out.print("Title: ");
        String title = sc.nextLine();

        if (title.isEmpty()) {
            throw new InvalidTaskException("Title cannot be empty");
        }

        System.out.print("Deadline (YYYY-MM-DD): ");
        LocalDate deadline = LocalDate.parse(sc.nextLine());

        System.out.print("Difficulty (1-5): ");
        int difficulty = Integer.parseInt(sc.nextLine());

        System.out.print("Estimated Hours: ");
        double hours = Double.parseDouble(sc.nextLine());

        Task task = null;

        if (type == 1) {
            task = new AssignmentTask(title, deadline, difficulty, hours);
        } else if (type == 2) {
            task = new ExamTask(title, deadline, difficulty, hours);
        } else if (type == 3) {
            task = new ReadingTask(title, deadline, difficulty, hours);
        }

        plannerService.addTask(task);
    }

    private void viewAllTasks() {

        List<Task> tasks = plannerService.getAllTasks();

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i));
        }
    }

    private void viewScheduledTasks() {

        List<Task> tasks = plannerService.getScheduledTasks();

        for (Task t : tasks) {
            System.out.println(t);
        }
    }

    private void changeStrategy() {

        System.out.println("1. Deadline");
        System.out.println("2. Difficulty");
        System.out.println("3. Balanced");

        int choice = Integer.parseInt(sc.nextLine());

        if (choice == 1) {
            plannerService.setStrategy(new DeadlineStrategy());
        } else if (choice == 2) {
            plannerService.setStrategy(new DifficultyStrategy());
        } else if (choice == 3) {
            plannerService.setStrategy(new BalancedStrategy());
        }
    }

    private void removeTask() {

        viewAllTasks();

        int index = Integer.parseInt(sc.nextLine());

        plannerService.removeTask(index);
    }
}