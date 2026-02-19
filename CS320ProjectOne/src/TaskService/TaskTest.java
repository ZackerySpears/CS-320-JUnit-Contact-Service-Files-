package TaskService;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    void testCreateTaskValid() {
        Task task = new Task("12345", "Do homework", "Finish Module Four milestone");
        assertEquals("12345", task.getTaskId());
        assertEquals("Do homework", task.getName());
        assertEquals("Finish Module Four milestone", task.getDescription());
    }

    @Test
    void testTaskIdNullThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                new Task(null, "Name", "Description"));
    }

    @Test
    void testTaskIdTooLongThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                new Task("12345678901", "Name", "Description")); // 11 chars
    }

    @Test
    void testNameNullThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                new Task("123", null, "Description"));
    }

    @Test
    void testNameTooLongThrows() {
        String longName = "123456789012345678901"; // 21 chars
        assertThrows(IllegalArgumentException.class, () ->
                new Task("123", longName, "Description"));
    }

    @Test
    void testDescriptionNullThrows() {
        assertThrows(IllegalArgumentException.class, () ->
                new Task("123", "Name", null));
    }

    @Test
    void testDescriptionTooLongThrows() {
        String longDesc = "123456789012345678901234567890123456789012345678901"; // 51 chars
        assertThrows(IllegalArgumentException.class, () ->
                new Task("123", "Name", longDesc));
    }

    @Test
    void testUpdateNameValid() {
        Task task = new Task("1", "Old", "Desc");
        task.setName("New Name");
        assertEquals("New Name", task.getName());
    }

    @Test
    void testUpdateNameInvalidThrows() {
        Task task = new Task("1", "Old", "Desc");
        assertThrows(IllegalArgumentException.class, () -> task.setName(null));
    }

    @Test
    void testUpdateDescriptionValid() {
        Task task = new Task("1", "Name", "Old Desc");
        task.setDescription("New Desc");
        assertEquals("New Desc", task.getDescription());
    }

    @Test
    void testUpdateDescriptionInvalidThrows() {
        Task task = new Task("1", "Name", "Old Desc");
        assertThrows(IllegalArgumentException.class, () -> task.setDescription(null));
    }

}
