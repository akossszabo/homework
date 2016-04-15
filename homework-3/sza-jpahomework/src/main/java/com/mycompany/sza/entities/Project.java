package com.mycompany.sza.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Project extends Activity implements Serializable {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String place;
    @Temporal(TemporalType.DATE)
    private Date established;
    @ManyToMany(mappedBy = "projects")
    private List<SoftwareEngineer> engineers;

    public Project() {
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

    
}
