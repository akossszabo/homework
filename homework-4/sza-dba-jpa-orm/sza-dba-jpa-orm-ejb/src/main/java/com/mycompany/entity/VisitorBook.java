package com.mycompany.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@NamedQuery (name="VisitorBook.getAllVisitorBook",query="SELECT v FROM VisitorBook v")
@Entity
public class VisitorBook implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    @JoinColumn(name = "park_fk")
    @ManyToOne
    private AmusementPark amPark;
    
    @OneToOne
    @JoinColumn(name = "visitor_fk")
    private Visitor visitor;

    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    private String registrationText;

    public VisitorBook() {
    }
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AmusementPark getAmPark() {
        return amPark;
    }

    public void setAmPark(AmusementPark park) {
        this.amPark = park;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getRegistrationText() {
        return registrationText;
    }

    public void setRegistrationText(String registrationText) {
        this.registrationText = registrationText;
    }
    
}
