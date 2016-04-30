package com.mycompany.entity;

import com.mycompany.enumerator.Type;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@NamedQueries({@NamedQuery(name = "Machine.getAllMachine",query="select m from Machine m"),
               @NamedQuery(name = "getMachinesOfPark", query = "SELECT m FROM Machine m WHERE m.ownerPark = :id")
               })
@Entity
public class Machine implements Serializable {
    
    @Id @GeneratedValue
    private long id;
    private String fantasyName;
    private int size;
    private int price;
    private int ticketPrice;
    private int numberOfSeats;
    private int minAge;
    @Enumerated(EnumType.STRING)
    private Type type;
    @ManyToOne
    @JoinColumn(name="PARK_ID")
    private AmusementPark ownerPark;
    
    @OneToMany(mappedBy = "machine")
    private List<Visitor> visitors;
    public Machine() {
    }

    public String getFantasyName() {
        return fantasyName;
    }

    public void setFantasyName(String fantasyName) {
        this.fantasyName = fantasyName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public AmusementPark getOwnerPark() {
        return ownerPark;
    }

    public void setOwnerPark(AmusementPark ownerPark) {
        this.ownerPark = ownerPark;
    }

    public List<Visitor> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<Visitor> visitors) {
        this.visitors = visitors;
    }
    
    
}
