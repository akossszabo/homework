package com.mycompany.sza.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class ProjectManager extends Person {
    
    ProgrammingLanguage programmingLanguage;
    @OneToMany(mappedBy = "projectManager")
    private List<Project> projects;

    public ProjectManager() {
        //empty constructor
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public ProgrammingLanguage getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(ProgrammingLanguage programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

}
