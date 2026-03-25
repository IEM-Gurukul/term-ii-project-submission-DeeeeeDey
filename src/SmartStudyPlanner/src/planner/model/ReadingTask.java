package planner.model;

import java.time.LocalDate;

public class ReadingTask extends Task {

    public ReadingTask(String title, LocalDate deadline, int difficulty, double estimatedHours) {
        super(title, deadline, difficulty, estimatedHours);
    }

    public String getType() {
        return "Reading";
    }
}