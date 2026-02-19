package ContactService;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ContactTest {

    @Test
    void createContact_validFields_success() {
        Contact c = new Contact("12345", "John", "Doe", "1234567890", "123 Main St");
        assertEquals("12345", c.getContactId());
        assertEquals("John", c.getFirstName());
        assertEquals("Doe", c.getLastName());
        assertEquals("1234567890", c.getPhone());
        assertEquals("123 Main St", c.getAddress());
    }

    @Test
    void createContact_contactIdNull_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact(null, "Zack", "Spears", "1234567890", "123 One St"));
    }

    @Test
    void createContact_contactIdTooLong_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("12345678901", "Zack", "Spears", "1234567890", "123 Piece St"));
    }

    @Test
    void createContact_firstNameNull_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", null, "Spears", "1234567890", "123 Main St"));
    }

    @Test
    void createContact_firstNameTooLong_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "ABCDEFGHIJK", "Spears", "1234567890", "123 Main St"));
    }

    @Test
    void createContact_lastNameNull_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "Zack", null, "1234567890", "123 Main St"));
    }

    @Test
    void createContact_lastNameTooLong_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "Zack", "ABCDEFGHIJK", "1234567890", "123 Main St"));
    }

    @Test
    void createContact_phoneNull_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "Zack", "Spears", null, "123 Main St"));
    }

    @Test
    void createContact_phoneNot10Digits_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "Zack", "Spears", "12345", "123 Main St"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "Zack", "Spears", "123456789A", "123 Main St"));
    }

    @Test
    void createContact_addressNull_throws() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "Zack", "Spears", "1234567890", null));
    }

    @Test
    void createContact_addressTooLong_throws() {
        String longAddress = "1234567890123456789012345678901"; // 31 chars
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("1", "Zack", "Spears", "1234567890", longAddress));
    }
}

