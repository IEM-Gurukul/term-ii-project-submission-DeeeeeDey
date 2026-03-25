package planner.model;

import java.time.LocalDate;

public abstract class Task {

    private String title;
    private LocalDate deadline;
    private int difficulty;
    private double estimatedHours;

    public Task(String title, LocalDate deadline, int difficulty, double estimatedHours) {
        this.title = title;
        this.deadline = deadline;
        this.difficulty = difficulty;
        this.estimatedHours = estimatedHours;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public double getEstimatedHours() {
        return estimatedHours;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setEstimatedHours(double estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    public boolean isOverdue() {
        return deadline.isBefore(LocalDate.now());
    }

    public abstract String getType();

    public String toFileString() {
        return getType() + "," + title + "," + deadline + "," + difficulty + "," + estimatedHours;
    }

    @Override
    public String toString() {
        return "[" + getType() + "] " + title + " | Deadline: " + deadline +
                " | Difficulty: " + difficulty + " | Hours: " + estimatedHours;
    }
}