package cs544;

import java.util.List;

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

        // // Create new instance of Owners and set values in them
        // Owner owner1 = new Owner("Dustin", "1000 N 4th St, Fairfield, Iowa");
        // // em.persist(owner1);
        // Owner owner2 = new Owner("Lita", "432 1A Highway, Ho Chi Minh, Vietnam");
        // // em.persist(owner2);

        // // Create new instance of Car and set values in it
        // Car car1 = new Car("BMW", "SDA231", 30221.00, owner1);
        // // save the car
        // em.persist(car1);
        // // Create new instance of Car and set values in it
        // Car car2 = new Car("Mercedes", "HOO100", 4088.00, owner2);
        // // save the car
        // em.persist(car2);

        em.getTransaction().commit();
        em.close();

        // =======================

        em = emf.createEntityManager();
        em.getTransaction().begin();

        // retieve all cars
        // TypedQuery<Car> query = em.createQuery("from Car", Car.class);
        // List<Car> carList = query.getResultList();
        // for (Car car : carList) {
        // System.out.println(
        // "brand= " + car.getBrand()
        // + ", year= " + car.getYear()
        // + ", price= " + car.getPrice()
        // + ", owner= " + car.getOwner().getName());
        // }
        em.getTransaction().commit();
        em.close();
    }
}
