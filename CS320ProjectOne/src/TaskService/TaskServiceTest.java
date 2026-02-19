package TaskService;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

public class TaskServiceTest {

    @Test
    void testAddTaskSuccess() {
        TaskService service = new TaskService();
        service.addTask("A1", "Task", "Description");

        Task stored = service.getTask("A1");
        assertNotNull(stored);
        assertEquals("A1", stored.getTaskId());
        assertEquals("Task", stored.getName());
        assertEquals("Description", stored.getDescription());
    }

    @Test
    void testAddTaskDuplicateIdThrows() {
        TaskService service = new TaskService();
        service.addTask("A1", "Task", "Description");

        assertThrows(IllegalArgumentException.class, () ->
                service.addTask("A1", "Another", "Another Desc"));
    }

    @Test
    void testDeleteTaskSuccess() {
        TaskService service = new TaskService();
        service.addTask("A1", "Task", "Description");

        service.deleteTask("A1");
        assertNull(service.getTask("A1"));
    }

    @Test
    void testDeleteTaskNotFoundThrows() {
        TaskService service = new TaskService();
        assertThrows(NoSuchElementException.class, () -> service.deleteTask("NOPE"));
    }

    @Test
    void testUpdateNameSuccess() {
        TaskService service = new TaskService();
        service.addTask("A1", "Old", "Description");

        service.updateName("A1", "New");
        assertEquals("New", service.getTask("A1").getName());
    }

    @Test
    void testUpdateDescriptionSuccess() {
        TaskService service = new TaskService();
        service.addTask("A1", "Name", "Old");

        service.updateDescription("A1", "New Desc");
        assertEquals("New Desc", service.getTask("A1").getDescription());
    }

    @Test
    void testUpdateNameNotFoundThrows() {
        TaskService service = new TaskService();
        assertThrows(NoSuchElementException.class, () -> service.updateName("X", "New"));
    }

    @Test
    void testUpdateDescriptionNotFoundThrows() {
        TaskService service = new TaskService();
        assertThrows(NoSuchElementException.class, () -> service.updateDescription("X", "New"));
    }

    @Test
    void testUpdateNameInvalidDoesNotChange() {
        TaskService service = new TaskService();
        service.addTask("A1", "Valid", "Description");

        assertThrows(IllegalArgumentException.class, () -> service.updateName("A1", null));
        assertEquals("Valid", service.getTask("A1").getName());
    }

    @Test
    void testUpdateDescriptionInvalidDoesNotChange() {
        TaskService service = new TaskService();
        service.addTask("A1", "Name", "Valid Desc");

        assertThrows(IllegalArgumentException.class, () -> service.updateDescription("A1", null));
        assertEquals("Valid Desc", service.getTask("A1").getDescription());
    }
}
