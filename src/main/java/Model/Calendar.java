/**
 * Anuwat Angkuldee 5810401066
 */

package Model;

import java.util.ArrayList;

public class Calendar {

    private ArrayList<Appointment> appointments = new ArrayList<>();

    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }

    public void addAppointment(int id, String name, String description, String date) {
        this.appointments.add(new Appointment(id, name, description, date));
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public Appointment getAppointment(int id) {
        for (Appointment apt : appointments) {
            if (apt.getId() == id)
                return apt;
        }
        return null;
    }
}
