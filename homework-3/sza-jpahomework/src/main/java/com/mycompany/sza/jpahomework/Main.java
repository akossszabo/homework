package com.mycompany.sza.jpahomework;

import com.mycompany.sza.entities.Address;
import com.mycompany.sza.entities.ProgrammingLanguage;
import com.mycompany.sza.entities.Project;
import com.mycompany.sza.entities.SoftwareEngineer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("myPU");
        EntityManager em = emf.createEntityManager();
        SoftwareEngineer en1 = new SoftwareEngineer();
        Address ad1 = new Address();
        ad1.setCountry("Hungary");
        ad1.setCity("Budapest");
        en1.setName("Gipsz Jakab");
        en1.setAddress(ad1);
        //en1.setId(1234567L);
        en1.setProgrammingLanguage(ProgrammingLanguage.JAVA);
        
        Project project1 = new Project();
        Project project2 = new Project();
        project1.setName("Mobile Webshop");
        project2.setName("NetBank Project");
        
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(en1);
        
        tx.commit();
        
        em.close();
        emf.close();
    }  
}
