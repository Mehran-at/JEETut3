package com.newthinktank;

import com.newthinktank.JEETut3.Customer;

import javax.persistence.*;
import java.util.List;

public class TestSystem {
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
    .createEntityManagerFactory("JEETut3");
    public static void main(String[] args) throws Exception{
        addCustomer(1, "Sue", "Smith");
        addCustomer(2, "Jack", "Daniels");
        addCustomer(3, "Martha", "Volks");
        addCustomer(4, "Smith", "Valentine");
        getCustomer(1);
        getCustomers();
        changeFName(4, "Mark");
        deleteCustomer(3);
        ENTITY_MANAGER_FACTORY.close();
    }
    public static void addCustomer(int id, String fname, String lname) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            Customer cust = new Customer();
            cust.setfName(fname);
            cust.setlName(lname);
            cust.setId(id);
            em.persist(cust);
            et.commit();
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
    }
    public static void getCustomer(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT c FROM Customer c WHERE c.id = :custID";
        TypedQuery<Customer> tq = em.createQuery(query, Customer.class);
        tq.setParameter("custID", id);
        Customer cust = null;
        try {
            cust = tq.getSingleResult();
            System.out.println(cust.getfName() + " " + cust.getlName());
        } catch (NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        }
        public static void getCustomers() {
            EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
            String strQuery = "SELECT c FROM Customer c WHERE c.id IS NOT NULL";
            TypedQuery<Customer> tq = em.createQuery(strQuery, Customer.class);
            List<Customer> custs;
            try {
                custs= tq.getResultList();
                custs.forEach(cust -> System.out.println(cust.getfName() + " " + cust.getlName()));
            } catch (NoResultException ex) {
                ex.printStackTrace();
            }
            finally {
                em.close();
            }
        }
    public static void changeFName(int id, String fname) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Customer cust = null;
        try {
            et = em.getTransaction();
            et.begin();
            cust = em.find(Customer.class, id);
            cust.setfName(fname);
            em.persist(cust);
            et.commit();
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
    }
    public static void deleteCustomer(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Customer cust = null;
        try {
            et = em.getTransaction();
            et.begin();
            cust = em.find(Customer.class, id);
            em.remove(cust);
            em.persist(cust);
            et.commit();
        } catch (Exception ex) {
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
    }
}
