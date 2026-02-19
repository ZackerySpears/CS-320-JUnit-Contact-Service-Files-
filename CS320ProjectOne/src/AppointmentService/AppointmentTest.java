package AppointmentService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class AppointmentTest {

    @Test
    void testAppointmentCreatedSuccessfully() {
        Date future = new Date(System.currentTimeMillis() + 60_000); // +1 minute
        Appointment appt = new Appointment("A123", future, "Checkup");

        assertEquals("A123", appt.getAppointmentId());
        assertEquals(future, appt.getAppointmentDate());
        assertEquals("Checkup", appt.getDescription());
    }

    // ---- Appointment ID tests ----
    @Test
    void testAppointmentIdNullThrows() {
        Date future = new Date(System.currentTimeMillis() + 60_000);
        assertThrows(IllegalArgumentException.class,
                () -> new Appointment(null, future, "Checkup"));
    }

    @Test
    void testAppointmentIdTooLongThrows() {
        Date future = new Date(System.currentTimeMillis() + 60_000);
        assertThrows(IllegalArgumentException.class,
                () -> new Appointment("TOO_LONG_ID_11", future, "Checkup"));
    }

    // ---- Date tests ----
    @Test
    void testAppointmentDateNullThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new Appointment("A123", null, "Checkup"));
    }

    @Test
    void testAppointmentDateInPastThrows() {
        Date past = new Date(System.currentTimeMillis() - 60_000); // -1 minute
        assertThrows(IllegalArgumentException.class,
                () -> new Appointment("A123", past, "Checkup"));
    }

    // ---- Description tests ----
    @Test
    void testDescriptionNullThrows() {
        Date future = new Date(System.currentTimeMillis() + 60_000);
        assertThrows(IllegalArgumentException.class,
                () -> new Appointment("A123", future, null));
    }

    @Test
    void testDescriptionTooLongThrows() {
        Date future = new Date(System.currentTimeMillis() + 60_000);
        String tooLong = "123456789012345678901234567890123456789012345678901"; // 51 chars
        assertEquals(51, tooLong.length());

        assertThrows(IllegalArgumentException.class,
                () -> new Appointment("A123", future, tooLong));
    }

    @Test
    void testSettersValidate() {
        Date future1 = new Date(System.currentTimeMillis() + 60_000);
        Date future2 = new Date(System.currentTimeMillis() + 120_000);

        Appointment appt = new Appointment("A123", future1, "Checkup");
        appt.setAppointmentDate(future2);
        assertEquals(future2, appt.getAppointmentDate());

        assertThrows(IllegalArgumentException.class,
                () -> appt.setAppointmentDate(new Date(System.currentTimeMillis() - 60_000)));

        appt.setDescription("Updated description");
        assertEquals("Updated description", appt.getDescription());

        String tooLong = "123456789012345678901234567890123456789012345678901"; // 51
        assertThrows(IllegalArgumentException.class,
                () -> appt.setDescription(tooLong));
    }
}
