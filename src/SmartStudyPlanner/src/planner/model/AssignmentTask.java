package planner.model;

import java.time.LocalDate;

public class AssignmentTask extends Task {

    public AssignmentTask(String title, LocalDate deadline, int difficulty, double estimatedHours) {
        super(title, deadline, difficulty, estimatedHours);
    }

    public String getType() {
        return "Assignment";
    }
}