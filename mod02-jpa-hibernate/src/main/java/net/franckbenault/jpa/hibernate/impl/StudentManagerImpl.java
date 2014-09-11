package net.franckbenault.jpa.hibernate.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import net.franckbenault.jpa.hibernate.Student;
import net.franckbenault.jpa.hibernate.StudentManager;

public class StudentManagerImpl implements StudentManager {

	public Student createStudent(Student student) {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAService");
	    EntityManager em = emf.createEntityManager();
	    em.getTransaction().begin();
	    em.persist(student);
	    em.getTransaction().commit();
	    return student;
	}

	public void removeStudent(Student student) {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAService");
	    EntityManager em = emf.createEntityManager();
	    em.getTransaction().begin();
	    student = em.find(Student.class,student.getId());
	    em.remove(student);
	    em.getTransaction().commit();
	   
	}

}
