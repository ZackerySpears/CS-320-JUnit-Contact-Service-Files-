package TaskService;

import java.util.Objects;

public class Task {
    private final String taskId;     
    private String name;
    private String description;

    public Task(String taskId, String name, String description) {
        validateTaskId(taskId);
        validateName(name);
        validateDescription(description);

        this.taskId = taskId;
        this.name = name;
        this.description = description;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // Updatable fields only
    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public void setDescription(String description) {
        validateDescription(description);
        this.description = description;
    }

    private static void validateTaskId(String taskId) {
        if (taskId == null) {
            throw new IllegalArgumentException("Task ID cannot be null.");
        }
        if (taskId.length() > 10) {
            throw new IllegalArgumentException("Task ID cannot be longer than 10 characters.");
        }
    }

    private static void validateName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null.");
        }
        if (name.length() > 20) {
            throw new IllegalArgumentException("Name cannot be longer than 20 characters.");
        }
    }

    private static void validateDescription(String description) {
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null.");
        }
        if (description.length() > 50) {
            throw new IllegalArgumentException("Description cannot be longer than 50 characters.");
        }
    }

    // Optional (handy for debugging/tests)
    @Override
    public String toString() {
        return "Task{id='" + taskId + "', name='" + name + "', description='" + description + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Task)) return false;
        Task other = (Task) o;
        return Objects.equals(taskId, other.taskId)
                && Objects.equals(name, other.name)
                && Objects.equals(description, other.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, name, description);
    }
}
