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

            int choice;

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input. Enter a number.");
                continue;
            }

            try {

                if (choice == 1) addTask();
                else if (choice == 2) viewAllTasks();
                else if (choice == 3) viewScheduledTasks();
                else if (choice == 4) changeStrategy();
                else if (choice == 5) removeTask();
                else if (choice == 6) reminderService.checkReminders(plannerService.getAllTasks());
                else if (choice == 7) {
                    repository.saveTasks(plannerService.getAllTasks());
                    break;
                } else {
                    System.out.println("Invalid choice");
                }

            } catch (InvalidTaskException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void addTask() throws InvalidTaskException {

        int type;

        try {
            System.out.println("1. Assignment  2. Exam  3. Reading");
            type = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid type");
            return;
        }

        System.out.print("Title: ");
        String title = sc.nextLine();

        if (title.isEmpty()) {
            throw new InvalidTaskException("Title cannot be empty");
        }

        LocalDate deadline;

        try {
            System.out.print("Deadline (YYYY-MM-DD): ");
            deadline = LocalDate.parse(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid date format");
            return;
        }

        int difficulty;

        try {
            System.out.print("Difficulty (1-5): ");
            difficulty = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid number");
            return;
        }

        if (difficulty < 1 || difficulty > 5) {
            System.out.println("Difficulty must be between 1 and 5");
            return;
        }

        double hours;

        try {
            System.out.print("Estimated Hours: ");
            hours = Double.parseDouble(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid number");
            return;
        }

        if (hours < 0) {
            System.out.println("Hours cannot be negative");
            return;
        }

        Task task = null;

        if (type == 1) task = new AssignmentTask(title, deadline, difficulty, hours);
        else if (type == 2) task = new ExamTask(title, deadline, difficulty, hours);
        else if (type == 3) task = new ReadingTask(title, deadline, difficulty, hours);
        else {
            System.out.println("Invalid task type");
            return;
        }

        plannerService.addTask(task);
        System.out.println("Task added");
    }

    private void viewAllTasks() {

        List<Task> tasks = plannerService.getAllTasks();

        if (tasks.isEmpty()) {
            System.out.println("No tasks found");
            return;
        }

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i));
        }
    }

    private void viewScheduledTasks() {

        List<Task> tasks = plannerService.getScheduledTasks();

        if (tasks.isEmpty()) {
            System.out.println("No tasks available");
            return;
        }

        for (Task t : tasks) {
            System.out.println(t);
        }
    }

    private void changeStrategy() {

        int choice;

        try {
            System.out.println("1. Deadline");
            System.out.println("2. Difficulty");
            System.out.println("3. Balanced");
            choice = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid input");
            return;
        }

        if (choice == 1) plannerService.setStrategy(new DeadlineStrategy());
        else if (choice == 2) plannerService.setStrategy(new DifficultyStrategy());
        else if (choice == 3) plannerService.setStrategy(new BalancedStrategy());
        else System.out.println("Invalid choice");
    }

    private void removeTask() {

        viewAllTasks();

        if (plannerService.isEmpty()) return;

        int index;

        try {
            System.out.print("Enter index: ");
            index = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid number");
            return;
        }

        if (!plannerService.removeTask(index)) {
            System.out.println("Invalid index");
        } else {
            System.out.println("Task removed");
        }
    }
}