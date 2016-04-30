package com.mycompany.entity;

import com.mycompany.enumerator.Condition;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@NamedQueries({@NamedQuery(name = "Visitor.getAllVisitor",query="select v from Visitor v")
               })
@Entity
public class Visitor implements Serializable {
    @Id @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private Condition condition;
    private int money;
    @Temporal(TemporalType.DATE)
    private Date timeOfEntry;
    @Transient
    private int age;
    private boolean active;
    @ManyToOne
    private AmusementPark amusementPark;
    @ManyToOne
    private Machine machine;


    public Visitor() {
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Date getTimeOfEntry() {
        return timeOfEntry;
    }

    public void setTimeOfEntry(Date timeOfEntry) {
        this.timeOfEntry = timeOfEntry;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public AmusementPark getAmusementPark() {
        return amusementPark;
    }

    public void setAmusementPark(AmusementPark amusementPark) {
        this.amusementPark = amusementPark;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }
    
    
    
}
