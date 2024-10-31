package cs544;

import java.util.List;
import java.util.Random;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class App {

    private static EntityManagerFactory emf;

    public static void main(String[] args) throws Exception {
        emf = Persistence.createEntityManagerFactory("cs544");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // retieve all studens
        TypedQuery<Students> query = em.createQuery("from cs544.Students", Students.class);
        List<Students> studentList = query.getResultList();
        for (Students student : studentList) {
            System.out.println("student's name= " + student.getName());
        }
        em.getTransaction().commit();
        em.close();

        // 2 ===========================================================
        em = emf.createEntityManager();
        em.getTransaction().begin();

        // Create new instance of student and set values in it
        Random random = new Random();
        int randomNumber = 100 + random.nextInt(900);

        String email = String.format("student%s@fox.com", randomNumber);
        Students student1 = new Students(randomNumber, "Student " + randomNumber, email, randomNumber + "");
        // save the student
        em.persist(student1);

        em.getTransaction().commit();
        em.close();

        // 3 ===========================================================
        em = emf.createEntityManager();
        em.getTransaction().begin();

        // retieve all studens
        query = em.createQuery("from cs544.Students", Students.class);
        studentList = query.getResultList();
        for (Students student : studentList) {
            System.out.println("student's name= " + student.getName());
        }
        em.getTransaction().commit();
        em.close();
    }
}
