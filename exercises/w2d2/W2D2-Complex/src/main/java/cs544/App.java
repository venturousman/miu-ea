package cs544;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class App {

    private static EntityManagerFactory emf;

    public static void main(String[] args) throws Exception {
        emf = Persistence.createEntityManagerFactory("cs544");

        // doctor
        Doctor doctor = new Doctor("Eye doctor", "Frank", "Brown");
        Patient patient = new Patient("John Doe", "100 Main Street", "23114", "Boston");
        Appointment appointment = new Appointment(LocalDate.of(2008, 5, 15), LocalDate.of(2008, 6, 12), 100);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(doctor);
        em.persist(patient);

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        em.persist(appointment);

        em.getTransaction().commit();
        em.close();

        // =======================

        em = emf.createEntityManager();
        em.getTransaction().begin();

        // retieve data
        TypedQuery<Appointment> query = em.createQuery("from Appointment", Appointment.class);
        List<Appointment> appointmentList = query.getResultList();
        for (Appointment apm : appointmentList) {
            System.out.println("date= " + apm.getAppdate());
            System.out.println("patient= " + apm.getPatient().getName());
            System.out.println("doctor= " + apm.getDoctor().getFullname());
        }

        em.getTransaction().commit();
        em.close();
    }
}
