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

        // Products
        Product product1 = new Product("IPhone 17 128gb",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.", 895.50);
        Product product2 = new Product("IPhone 17 Plus 128gb",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.", 992.55);

        // OrderLines
        OrderLine orderLine1 = new OrderLine(1);
        OrderLine orderLine2 = new OrderLine(2);

        // Orders
        Order order1 = new Order(LocalDate.of(2024, 2, 15));
        Order order2 = new Order(LocalDate.of(2024, 6, 25));

        // Customers
        Customer customer1 = new Customer("Alice", "Johnson");
        Customer customer2 = new Customer("Bob", "Smith");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // persist products
        em.persist(product1);
        em.persist(product2);

        // persist orderlines
        orderLine1.setProduct(product2);
        orderLine2.setProduct(product1);

        em.persist(orderLine1);
        em.persist(orderLine2);

        // persist customers
        em.persist(customer1);
        em.persist(customer2);

        // persist orders
        order1.setCustomer(customer1);
        order1.setOrderLines(List.of(orderLine1));
        order2.setCustomer(customer2);
        order2.setOrderLines(List.of(orderLine2));

        em.persist(order1);
        em.persist(order2);

        em.getTransaction().commit();
        em.close();

        // ==============================================

        em = emf.createEntityManager();
        em.getTransaction().begin();

        // retieve data
        TypedQuery<Customer> query = em.createQuery("from Customer", Customer.class);
        List<Customer> customerList = query.getResultList();
        for (Customer customer : customerList) {
            System.out.println("firstname= " + customer.getFirstname()
                    + "; lastname= " + customer.getLastname());
        }

        em.getTransaction().commit();
        em.close();

        // ==============================================
        emf.close();
    }
}
