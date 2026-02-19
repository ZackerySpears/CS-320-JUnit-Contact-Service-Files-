package ContactService;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ContactServiceTest {

    @Test
    void addContact_uniqueId_success() {
        ContactService service = new ContactService();
        Contact c = new Contact("1", "Zack", "Spears", "1234567890", "123 Main St");

        service.addContact(c);

        assertNotNull(service.getContact("1"));
        assertEquals("Zack", service.getContact("1").getFirstName());
    }

    @Test
    void addContact_duplicateId_throws() {
        ContactService service = new ContactService();
        service.addContact(new Contact("1", "Zack", "Spears", "1234567890", "123 Main St"));

        assertThrows(IllegalArgumentException.class, () ->
                service.addContact(new Contact("1", "Tammy", "Roe", "0987654321", "456 Oak Ave")));
    }

    @Test
    void deleteContact_existingId_success() {
        ContactService service = new ContactService();
        service.addContact(new Contact("1", "Zack", "Doe", "1234567890", "123 Main St"));

        service.deleteContact("1");

        assertNull(service.getContact("1"));
    }

    @Test
    void deleteContact_missingId_throws() {
        ContactService service = new ContactService();
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact("nope"));
    }

    @Test
    void updateFirstName_success() {
        ContactService service = new ContactService();
        service.addContact(new Contact("1", "John", "Doe", "1234567890", "123 Main St"));

        service.updateFirstName("1", "Mike");

        assertEquals("Mike", service.getContact("1").getFirstName());
    }

    @Test
    void updateLastName_success() {
        ContactService service = new ContactService();
        service.addContact(new Contact("1", "John", "Doe", "1234567890", "123 Main St"));

        service.updateLastName("1", "Smith");

        assertEquals("Smith", service.getContact("1").getLastName());
    }

    @Test
    void updatePhone_success() {
        ContactService service = new ContactService();
        service.addContact(new Contact("1", "John", "Doe", "1234567890", "123 Main St"));

        service.updatePhone("1", "0987654321");

        assertEquals("0987654321", service.getContact("1").getPhone());
    }

    @Test
    void updateAddress_success() {
        ContactService service = new ContactService();
        service.addContact(new Contact("1", "John", "Doe", "1234567890", "123 Main St"));

        service.updateAddress("1", "999 New Address Blvd");

        assertEquals("999 New Address Blvd", service.getContact("1").getAddress());
    }

    @Test
    void update_missingId_throws() {
        ContactService service = new ContactService();
        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("x", "Bob"));
    }

    @Test
    void update_invalidValue_throws() {
        ContactService service = new ContactService();
        service.addContact(new Contact("1", "John", "Doe", "1234567890", "123 Main St"));

        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("1", "123")); // not 10 digits
        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("1", null)); // required
        assertThrows(IllegalArgumentException.class, () -> service.updateAddress("1", "1234567890123456789012345678901")); // 31 chars
    }
}
