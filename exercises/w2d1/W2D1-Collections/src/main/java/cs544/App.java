package cs544;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App {

    private static EntityManagerFactory emf;

    public static void main(String[] args) throws Exception {

        emf = Persistence.createEntityManagerFactory("cs544");

        // Create laptops and employee
        var employee = new Employee("Dustin", "Nguyen");
        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop("Dell", "XPS 13", employee));
        laptops.add(new Laptop("Apple", "MacBook Pro", employee));
        laptops.add(new Laptop("HP", "Spectre x360", employee));
        employee.setLaptops(laptops);

        // Create flights and passenger
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("FL001", "DSM", "DEN", LocalDate.now().plusDays(30)));
        flights.add(new Flight("FL002", "DEN", "SGN", LocalDate.now().plusDays(31)));
        var passenger = new Passenger("Dustin", flights);

        // Create School
        School school = new School();
        school.setName("MIU");

        // Create Students and add them to the map
        Student student1 = new Student(1, "Alice", "Smith");
        Student student2 = new Student(2, "Bob", "Johnson");

        // Add students to the map
        Map<Integer, Student> students = new HashMap<>();
        students.put(student1.getStudentid(), student1);
        students.put(student2.getStudentid(), student2);
        school.setStudents(students);

        // persist to DB
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(employee);
        em.persist(passenger);
        em.persist(school);

        em.getTransaction().commit();
        em.close();

        emf.close();
    }
}
