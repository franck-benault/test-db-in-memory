package net.franckbenault.jpa.hibernate.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import net.franckbenault.jpa.hibernate.Student;
import net.franckbenault.jpa.hibernate.StudentManager;

public class StudentManagerImpl implements StudentManager {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public StudentManagerImpl(String persistenceUnitName) {
	    emf = Persistence.createEntityManagerFactory(persistenceUnitName);
	    em = emf.createEntityManager();		
	}

	public Student createStudent(Student student) {

	    em.getTransaction().begin();
	    em.persist(student);
	    em.getTransaction().commit();
	    return student;
	}

	public void removeStudent(Student student) {
	    em.getTransaction().begin();
	    student = em.find(Student.class,student.getId());
	    em.remove(student);
	    em.getTransaction().commit();
	   
	}

}
