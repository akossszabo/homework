package com.mycompany.sza.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
    @NamedQuery(name = "Project.findWebShopbyName",
            query = "Select p.description FROM Project p WHERE p.name = :webshopName "),
    @NamedQuery(name = "Project.findProjectbyPlace",
            query = "Select p.name FROM Project p WHERE p.place = :place "),
    @NamedQuery(name ="Project.findByManager",
            query = "Select p.name FROM Project p WHERE p.projectManager.name ='Git Áron' ")
})
public class Project extends Activity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String place;
    @Temporal(TemporalType.DATE)
    private Date established;
    @ManyToMany(mappedBy = "projects")
    private List<SoftwareEngineer> engineers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PMANAGER_ID")
    private ProjectManager projectManager;

    public Project() {
        //empty constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getEstablished() {
        return established;
    }

    public void setEstablished(Date established) {
        this.established = established;
    }

    public List<SoftwareEngineer> getEngineers() {
        return engineers;
    }

    public void setEngineers(List<SoftwareEngineer> engineers) {
        this.engineers = engineers;
    }

    public ProjectManager getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }

    @Override
    public String toString() {
        return "Project{" + "id=" + id + ", name=" + name + ", place=" + place + ", established=" + established + ", engineers=" + engineers + ", projectManager=" + projectManager + '}';
    }
    
    
}
