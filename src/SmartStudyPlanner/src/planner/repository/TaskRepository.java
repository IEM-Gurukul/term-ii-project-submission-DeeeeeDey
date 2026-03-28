package planner.repository;

import planner.model.Task;
import planner.model.AssignmentTask;
import planner.model.ExamTask;
import planner.model.ReadingTask;

import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.time.LocalDate;

public class TaskRepository {

    private String filePath = "src/SmartStudyPlanner/data/tasks.txt";

    public void saveTasks(List<Task> tasks) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            for (Task task : tasks) {
                writer.write(task.toFileString());
                writer.newLine();
            }

            writer.close();

        } catch (Exception e) {
            System.out.println("Error saving tasks");
        }
    }

    public List<Task> loadTasks() {

        List<Task> tasks = new ArrayList<Task>();

        File file = new File(filePath);

        if (!file.exists()) {
            return tasks;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;

            while ((line = reader.readLine()) != null) {

                try {

                    String[] parts = line.split(",");

                    if (parts.length != 5) {
                        continue;
                    }

                    String type = parts[0];
                    String title = parts[1];
                    LocalDate deadline = LocalDate.parse(parts[2]);
                    int difficulty = Integer.parseInt(parts[3]);
                    double hours = Double.parseDouble(parts[4]);

                    Task task = null;

                    if (type.equals("Assignment")) {
                        task = new AssignmentTask(title, deadline, difficulty, hours);
                    } else if (type.equals("Exam")) {
                        task = new ExamTask(title, deadline, difficulty, hours);
                    } else if (type.equals("Reading")) {
                        task = new ReadingTask(title, deadline, difficulty, hours);
                    }

                    if (task != null) {
                        tasks.add(task);
                    }

                } catch (Exception e) {
                    continue;
                }
            }

            reader.close();

        } catch (Exception e) {
            System.out.println("Error loading tasks");
        }

        return tasks;
    }
}