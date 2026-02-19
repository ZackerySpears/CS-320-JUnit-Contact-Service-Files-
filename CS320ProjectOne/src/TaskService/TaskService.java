package TaskService;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class TaskService {

    private final Map<String, Task> tasks = new HashMap<>();

    // Add by providing fields (common SNHU style)
    public void addTask(String taskId, String name, String description) {
        if (tasks.containsKey(taskId)) {
            throw new IllegalArgumentException("Task ID already exists: " + taskId);
        }
        Task task = new Task(taskId, name, description);
        tasks.put(taskId, task);
    }

    // Add by object (optional convenience)
    public void addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null.");
        }
        String id = task.getTaskId();
        if (tasks.containsKey(id)) {
            throw new IllegalArgumentException("Task ID already exists: " + id);
        }
        tasks.put(id, task);
    }

    public void deleteTask(String taskId) {
        if (taskId == null) {
            throw new IllegalArgumentException("Task ID cannot be null.");
        }
        if (!tasks.containsKey(taskId)) {
            throw new NoSuchElementException("Task ID not found: " + taskId);
        }
        tasks.remove(taskId);
    }

    public void updateName(String taskId, String newName) {
        Task task = getExistingTask(taskId);
        task.setName(newName);
    }

    public void updateDescription(String taskId, String newDescription) {
        Task task = getExistingTask(taskId);
        task.setDescription(newDescription);
    }

    // Helpful for testing / verification
    public Task getTask(String taskId) {
        if (taskId == null) {
            throw new IllegalArgumentException("Task ID cannot be null.");
        }
        return tasks.get(taskId);
    }

    private Task getExistingTask(String taskId) {
        if (taskId == null) {
            throw new IllegalArgumentException("Task ID cannot be null.");
        }
        Task task = tasks.get(taskId);
        if (task == null) {
            throw new NoSuchElementException("Task ID not found: " + taskId);
        }
        return task;
    }
}
