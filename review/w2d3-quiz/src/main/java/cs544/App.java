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

        Wheel wheel1 = new Wheel(1L, 17, 20);
        Wheel wheel2 = new Wheel(2L, 17, 20);
        Bike bike = new Bike("John Doe", "Trek", "Domane SL7", 2023, "Black", 22);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(wheel1);
        em.persist(wheel2);

        bike.setWheels(List.of(wheel1, wheel2));
        em.persist(bike);

        em.getTransaction().commit();
        em.close();

        // ==============================================

        em = emf.createEntityManager();
        em.getTransaction().begin();

        // retieve data
        TypedQuery<Vehicle> vehicleQuery = em.createQuery("from Vehicle", Vehicle.class);
        List<Vehicle> vehicleList = vehicleQuery.getResultList();
        for (Vehicle vehicle : vehicleList) {
            System.out.println("owner= " + vehicle.getOwner());
            System.out.println("manufacturer= " + vehicle.getMake());
            System.out.println("model= " + vehicle.getModel());
            System.out.println("color= " + vehicle.getColor());
            System.out.println("year= " + vehicle.getYear());
            if (vehicle instanceof Bike) {
                Bike mBike = (Bike) vehicle;
                System.out.println("numgears= " + mBike.getNumgears());
            }
            System.out.println("-----------------------------------");
        }

        em.getTransaction().commit();
        em.close();

        // ==============================================
        emf.close();
    }
}
