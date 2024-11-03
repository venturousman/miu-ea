package cs544;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class AppCar {

    private static EntityManagerFactory emf;

    public static void main(String[] args) throws Exception {
        emf = Persistence.createEntityManagerFactory("cs544");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Create new instance of Car and set values in it
        Car car1 = new Car("BMW", "SDA231", 30221.00);
        // save the car
        em.persist(car1);
        // Create new instance of Car and set values in it
        Car car2 = new Car("Mercedes", "HOO100", 4088.00);
        // save the car
        em.persist(car2);

        // Part A
        em.flush();
        if (em.contains(car1))
            System.out.println("EM contains Car1 before clear");
        em.clear();
        if (em.contains(car1))
            System.out.println("EM contains Car1 after clear");

        // Part B
        car1.setPrice(50000);
        System.out.println("EM contains Car1 before merge: " + em.contains(car1));
        // em.merge(car1);
        car1 = em.merge(car1);
        System.out.println("EM contains Car1 after merge: " + em.contains(car1));
        car1.setYear("2022");

        em.getTransaction().commit();
        em.close();

        // =====================
        em = emf.createEntityManager();
        em.getTransaction().begin();
        // retieve all cars
        TypedQuery<Car> query = em.createQuery("from Car", Car.class);
        List<Car> carList = query.getResultList();
        for (Car car : carList) {
            System.out.println("brand= " + car.getBrand() + ", year= "
                    + car.getYear() + ", price= " + car.getPrice());
        }
        em.getTransaction().commit();
        em.close();

    }
}
