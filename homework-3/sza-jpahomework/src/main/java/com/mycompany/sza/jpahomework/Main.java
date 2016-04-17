package com.mycompany.sza.jpahomework;

import com.mycompany.sza.entities.Address;
import com.mycompany.sza.entities.ProgrammingLanguage;
import com.mycompany.sza.entities.Project;
import com.mycompany.sza.entities.ProjectManager;
import com.mycompany.sza.entities.SoftwareEngineer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.logging.Logger;
import javax.persistence.Query;

public class Main {

    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    private Main() {
        //empty constructor
    }
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
        EntityManager em = emf.createEntityManager();

        SoftwareEngineer en1 = new SoftwareEngineer();
        Address ad1 = new Address();
        ad1.setCountry("Hungary");
        ad1.setCity("Budapest");
        en1.setName("Gipsz Jakab");
        en1.setAddress(ad1);
        en1.setProgrammingLanguage(ProgrammingLanguage.JAVA);

        SoftwareEngineer en2 = new SoftwareEngineer();
        Address ad2 = new Address();
        ad2.setCity("Győr");
        ad2.setCountry("Hungary");
        en2.setName("Trab Antal");
        en2.setAddress(ad2);
        en2.setProgrammingLanguage(ProgrammingLanguage.CPP);

        Project project1 = new Project();
        Project project2 = new Project();
        project1.setName("Mobile Webshop");
        project1.setDescription("A brand new Webshop Project write in Java EE");
        project2.setName("NetBank Project");

        ProjectManager pm1 = new ProjectManager();
        pm1.setName("Git Áron");
        pm1.setAddress(ad1);
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(en1);
        em.persist(en2);
        em.persist(project1);
        em.persist(project2);
        em.persist(pm1);
        

        List<Project> projects = new ArrayList<>();

        projects.add(project1);
        projects.add(project2);
        project1.setProjectManager(pm1);
        project2.setProjectManager(pm1);

        List<SoftwareEngineer> engineers = new ArrayList<>();
        engineers.add(en1);
        engineers.add(en2);
        project1.setEngineers(engineers);
        en1.setProjects(Arrays.asList(project2));
        en2.setProjects(projects);
        tx.commit();

        //Query-k lefuttatása
        tx.begin();

        TypedQuery<SoftwareEngineer> languageOfEngineer = em.createNamedQuery(
                "SoftwareEngineer.findByLanguage", SoftwareEngineer.class);
        languageOfEngineer.setParameter("language", ProgrammingLanguage.JAVA);
        LOG.info("Projects write in Java: " + languageOfEngineer.getResultList().toString());
        tx.commit();
        
        tx.begin();
        
        TypedQuery<String> webshopByName = em.createNamedQuery(
                "Project.findWebShopbyName", String.class);
        webshopByName.setParameter("webshopName","Mobile Webshop");
        LOG.info("Mobile Webshop description :" + webshopByName.getResultList().toString());
        
        tx.commit();
        
        tx.begin();
        TypedQuery<String> findByManager = em.createNamedQuery(
                "Project.findByManager", String.class);
        LOG.info("Project Manager Git Áron's projects: " + findByManager.getResultList().toString());
        
        tx.commit();
        
        tx.begin();
        TypedQuery<String> findName = em.createNamedQuery(
                "SoftwareEngineer.name", String.class);
        LOG.info("Engineers' name : " + findName.getResultList().toString());
        
        tx.commit();
        
        tx.begin();
        TypedQuery<ProjectManager> findManagers = em.createNamedQuery(
                "ProjectManager.fromBudapest", ProjectManager.class);
        LOG.info("ProjectManagers from Budapest: " + findManagers.getResultList().toString());
        
        tx.commit();
        

        em.close();
        emf.close();
    }
}
