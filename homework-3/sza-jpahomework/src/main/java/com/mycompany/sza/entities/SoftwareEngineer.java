package com.mycompany.sza.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class SoftwareEngineer extends Person{
    @Enumerated(EnumType.STRING)
    private ProgrammingLanguage programmingLanguage;
    @ManyToMany
    @JoinTable(name= "jnd_engineers_projects", 
            joinColumns= @JoinColumn(name= "engineer_fk"), 
            inverseJoinColumns= @JoinColumn(name="project_fk"))
    private List<Project> projects;

    public SoftwareEngineer() {
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
    
    
}
