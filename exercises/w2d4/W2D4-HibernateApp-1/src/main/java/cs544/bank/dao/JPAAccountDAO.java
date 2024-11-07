package cs544.bank.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cs544.bank.EntityManagerHelper;
import cs544.bank.domain.Account;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class JPAAccountDAO implements IAccountDAO {

    @Override
    public void saveAccount(Account account) {
        EntityManager em = EntityManagerHelper.getCurrent();

        em.getTransaction().begin();

        em.persist(account);

        em.getTransaction().commit();
    }

    @Override
    public void updateAccount(Account account) {
        EntityManager em = EntityManagerHelper.getCurrent();

        em.getTransaction().begin();

        Account accountexist = loadAccount(account.getAccountnumber());
        if (accountexist != null) {
            em.remove(accountexist); // remove the old
            em.persist(account); // add the new
        } else {
            System.out.println("Account not found for update");
        }

        em.getTransaction().commit();
    }

    @Override
    public Account loadAccount(long accountnumber) {
        EntityManager em = EntityManagerHelper.getCurrent();

        EntityGraph<Account> graph = em.createEntityGraph(Account.class);
        graph.addAttributeNodes("customer");
        graph.addSubgraph("entryList");

        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.fetchgraph", graph);

        Account foundAccount = em.find(Account.class, accountnumber, properties);
        return foundAccount;
    }

    @Override
    public Collection<Account> getAccounts() {
        EntityManager em = EntityManagerHelper.getCurrent();

        EntityGraph<Account> graph = em.createEntityGraph(Account.class);
        graph.addAttributeNodes("customer");
        graph.addSubgraph("entryList");

        TypedQuery<Account> query = em.createQuery("from Account", Account.class);
        query.setHint("javax.persistence.fetchgraph", graph);

        List<Account> customers = query.getResultList();
        return customers;
    }

}
