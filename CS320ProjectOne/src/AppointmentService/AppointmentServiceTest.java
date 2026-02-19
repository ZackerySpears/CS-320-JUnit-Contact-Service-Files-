package AppointmentService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class AppointmentServiceTest {

    @Test
    void testAddAppointmentSuccessfully() {
        AppointmentService service = new AppointmentService();
        Date future = new Date(System.currentTimeMillis() + 60_000);

        Appointment appt = new Appointment("A1", future, "Dentist");
        service.addAppointment(appt);

        assertEquals(1, service.size());
        assertNotNull(service.getAppointment("A1"));
        assertEquals("Dentist", service.getAppointment("A1").getDescription());
    }

    @Test
    void testAddAppointmentNullThrows() {
        AppointmentService service = new AppointmentService();
        assertThrows(IllegalArgumentException.class, () -> service.addAppointment(null));
    }

    @Test
    void testAddAppointmentDuplicateIdThrows() {
        AppointmentService service = new AppointmentService();
        Date future = new Date(System.currentTimeMillis() + 60_000);

        service.addAppointment(new Appointment("A1", future, "Dentist"));
        assertThrows(IllegalArgumentException.class,
                () -> service.addAppointment(new Appointment("A1", future, "Doctor")));
    }

    @Test
    void testDeleteAppointmentSuccessfully() {
        AppointmentService service = new AppointmentService();
        Date future = new Date(System.currentTimeMillis() + 60_000);

        service.addAppointment(new Appointment("A1", future, "Dentist"));
        assertEquals(1, service.size());

        service.deleteAppointment("A1");
        assertEquals(0, service.size());
        assertNull(service.getAppointment("A1"));
    }

    @Test
    void testDeleteAppointmentNullIdThrows() {
        AppointmentService service = new AppointmentService();
        assertThrows(IllegalArgumentException.class, () -> service.deleteAppointment(null));
    }

    @Test
    void testDeleteAppointmentNonexistentIdThrows() {
        AppointmentService service = new AppointmentService();
        assertThrows(IllegalArgumentException.class, () -> service.deleteAppointment("NOPE"));
    }
}
