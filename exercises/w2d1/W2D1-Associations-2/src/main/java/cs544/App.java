package cs544;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App {

    private static EntityManagerFactory emf;

    public static void main(String[] args) throws Exception {
        emf = Persistence.createEntityManagerFactory("cs544");

        // Create Department
        Department department = new Department("Human Resources");

        // Create Employees
        Employee employee1 = new Employee("Alice Johnson");
        Employee employee2 = new Employee("Bob Smith");

        // Set department reference in employees
        employee1.setDepartment(department);
        employee2.setDepartment(department);

        // Add employees to the department's list
        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        department.setEmployees(employees);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(department);
        em.getTransaction().commit();
        em.close();

        // ===============================================
        // Create a Publisher
        Publisher publisher = new Publisher("Penguin Random House");

        // Persist the Publisher
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(publisher);
        em.getTransaction().commit();
        em.close();

        // Create a Book with a Publisher
        Book bookWithPublisher = new Book("978-3-16-148410-0", "Effective Java", "Joshua Bloch");
        bookWithPublisher.setPublisher(publisher);

        // Create a Book without a Publisher
        Book bookWithoutPublisher = new Book("978-0-321-35668-0", "Clean Code", "Robert C. Martin");

        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(bookWithPublisher);
        em.persist(bookWithoutPublisher);
        em.getTransaction().commit();
        em.close();

        // ===============================================
        // Create Students
        Student student1 = new Student(1, "Alice", "Johnson");
        Student student2 = new Student(2, "Bob", "Smith");

        // Create Courses
        Course course1 = new Course("CS101", "Intro to Computer Science");
        Course course2 = new Course("MATH201", "Calculus I");

        // Set up associations
        List<Student> studentsInCourse1 = new ArrayList<>();
        studentsInCourse1.add(student1);
        studentsInCourse1.add(student2);
        course1.setStudents(studentsInCourse1);

        List<Student> studentsInCourse2 = new ArrayList<>();
        studentsInCourse2.add(student1);
        course2.setStudents(studentsInCourse2);

        // Set courses for each student
        List<Course> coursesForStudent1 = new ArrayList<>();
        coursesForStudent1.add(course1);
        coursesForStudent1.add(course2);
        student1.setCourses(coursesForStudent1);

        List<Course> coursesForStudent2 = new ArrayList<>();
        coursesForStudent2.add(course1);
        student2.setCourses(coursesForStudent2);

        // Persist the entities
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(course1); // Persist courses (students will be cascaded if needed)
        em.persist(course2);
        em.persist(student1); // Persist students if not cascaded
        em.persist(student2);
        em.getTransaction().commit();
        em.close();

        // ===============================================
        // Create Customer
        Customer customer = new Customer("John Doe");

        // Create Reservations
        Reservation reservation1 = new Reservation(LocalDate.of(2024, 11, 10), bookWithPublisher);
        Reservation reservation2 = new Reservation(LocalDate.of(2024, 12, 15), bookWithoutPublisher);

        // Add Reservations to Customer
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation1);
        reservations.add(reservation2);
        customer.setReservations(reservations);

        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(customer); // Persist Customer (Reservations will be cascaded)
        em.getTransaction().commit();
        em.close();

        // ===============================================
        // Create Office
        Office office = new Office("Agiro Building");

        em = emf.createEntityManager();
        em.getTransaction().begin();

        // Persist the Office first
        em.persist(office);

        // the issue: detached entity passed to persist: cs544.Employee
        // dont work
        // employee1 = em.find(Employee.class, employee1.getEmployeenumber());
        // employee2 = em.find(Employee.class, employee2.getEmployeenumber());

        // Reattach employees to the current EntityManager
        employee1 = em.merge(employee1);
        employee2 = em.merge(employee2);

        // Set Office for Employees
        employee1.setOffice(office);
        employee2.setOffice(office);

        // Add Employees to Office
        employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        office.setEmployees(employees);

        em.getTransaction().commit();
        em.close();

        // ===============================================
        emf.close();
    }
}
