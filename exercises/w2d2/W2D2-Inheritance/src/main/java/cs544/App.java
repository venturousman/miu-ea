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
        // Product product1 = new Product("IPhone 17 128gb",
        // "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        // 895.50);
        // Product product2 = new Product("IPhone 17 Plus 128gb",
        // "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        // 992.55);
        Book book1 = new Book(
                "Book 1",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                35,
                "Clean Code: A Handbook of Agile Software Craftsmanship");
        DVD dvd1 = new DVD(
                "DVD 1",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                16,
                "Comedy");
        CD cd1 = new CD(
                "CD 1",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                11.5,
                "Charles Petzold");

        // OrderLines
        OrderLine orderLine1 = new OrderLine(1);
        OrderLine orderLine2 = new OrderLine(2);
        OrderLine orderLine3 = new OrderLine(1);

        // Orders
        Order order1 = new Order(LocalDate.of(2024, 2, 15));
        Order order2 = new Order(LocalDate.of(2024, 6, 25));

        // Customers
        Customer customer1 = new Customer("Alice", "Johnson");
        Customer customer2 = new Customer("Bob", "Smith");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // persist products
        // em.persist(product1);
        // em.persist(product2);
        em.persist(book1);
        em.persist(dvd1);
        em.persist(cd1);

        // persist orderlines
        orderLine1.setProduct(dvd1);
        orderLine2.setProduct(book1);
        orderLine3.setProduct(cd1);

        em.persist(orderLine1);
        em.persist(orderLine2);
        em.persist(orderLine3);

        // persist customers
        em.persist(customer1);
        em.persist(customer2);

        // persist orders
        order1.setCustomer(customer1);
        order1.setOrderLines(List.of(orderLine1, orderLine3));
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
        TypedQuery<Customer> customerQuery = em.createQuery("from Customer", Customer.class);
        List<Customer> customerList = customerQuery.getResultList();
        for (Customer customer : customerList) {
            System.out.println("firstname= " + customer.getFirstname()
                    + "; lastname= " + customer.getLastname());
            System.out.println("-----------------------------------");
        }

        TypedQuery<Product> productQuery = em.createQuery("from Product", Product.class);
        List<Product> productList = productQuery.getResultList();
        for (Product product : productList) {
            String msg = "name= " + product.getName()
                    + "; price= " + product.getPrice();

            // Check for specific subclass fields
            if (product instanceof Book) {
                Book book = (Book) product;
                msg += "; title= " + book.getTitle();
            } else if (product instanceof DVD) {
                DVD dvd = (DVD) product;
                msg += "; genre= " + dvd.getGenre();
            } else if (product instanceof CD) {
                CD cd = (CD) product;
                msg += "; artist= " + cd.getArtist();
            }

            System.out.println(msg);
            System.out.println("-----------------------------------");
        }

        em.getTransaction().commit();
        em.close();

        // ==============================================
        emf.close();
    }
}
