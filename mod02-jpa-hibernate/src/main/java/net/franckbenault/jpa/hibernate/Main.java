package net.franckbenault.jpa.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
  public static void main(String[] a) throws Exception {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAService");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    
    
    em.persist(new Student());
    em.flush();
    em.persist(new Student());
    em.flush();

    
    
    em.getTransaction().commit();
    Helper.checkData();
    em.close();
    emf.close();
  }
}