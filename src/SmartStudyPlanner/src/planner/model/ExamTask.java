package planner.model;

import java.time.LocalDate;

public class ExamTask extends Task {

    public ExamTask(String title, LocalDate deadline, int difficulty, double estimatedHours) {
        super(title, deadline, difficulty, estimatedHours);
    }

    public String getType() {
        return "Exam";
    }
}