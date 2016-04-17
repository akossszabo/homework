package com.mycompany.sza.entities;

import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "SoftwareEngineer.countOfProjects",
            query = "SELECT COUNT(en1.projects) FROM SoftwareEngineer en1"),
    @NamedQuery(name = "SoftwareEngineer.findByLanguage",
            query = "SELECT en FROM SoftwareEngineer en WHERE en.programmingLanguage = :language"),
    @NamedQuery(name = "SoftwareEngineer.name",
            query= "Select s.name FROM SoftwareEngineer s"),
    @NamedQuery(name = "SoftwareEngineer.nameAndId",
            query = "Select s.name , s.id FROM SoftwareEngineer s")
    
})
public class SoftwareEngineer extends Person{
    @Enumerated(EnumType.STRING)
    private ProgrammingLanguage programmingLanguage;
    @ManyToMany
    @JoinTable(name= "jnd_engineers_projects", 
            joinColumns= @JoinColumn(name= "engineer_fk"), 
            inverseJoinColumns= @JoinColumn(name="project_fk"))
    private List<Project> projects;

    public SoftwareEngineer() {
        //empty constructor
    }

    public ProgrammingLanguage getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(ProgrammingLanguage programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "SoftwareEngineer{" + "programmingLanguage=" + programmingLanguage + ", projects=" + projects + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.programmingLanguage);
        hash = 97 * hash + Objects.hashCode(this.projects);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SoftwareEngineer other = (SoftwareEngineer) obj;
        if (this.programmingLanguage != other.programmingLanguage) {
            return false;
        }
        if (!Objects.equals(this.projects, other.projects)) {
            return false;
        }
        return true;
    }

 
}
