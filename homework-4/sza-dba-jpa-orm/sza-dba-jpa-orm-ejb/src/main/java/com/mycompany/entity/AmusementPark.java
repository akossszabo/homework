package com.mycompany.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({@NamedQuery(name = "AmusementPark.getAllParks",query="select park from AmusementPark park"),
               @NamedQuery(name = "AmusementPark.findParkById", query = "SELECT park from AmusementPark park WHERE park.id = :id")
})
public class AmusementPark implements Serializable {
    @Id @GeneratedValue
    private long id;
    private String parkName;
    private int capital;
    private int completeArea;
    
    private int ticketPrice;
    @Embedded 
    private Address address;

    @OneToMany(mappedBy="ownerPark")
    private List<Machine> machines;
    
    
    @OneToMany(mappedBy = "amPark")
    private List<VisitorBook> entrys;
    
    
    @OneToMany(mappedBy = "amusementPark")
    private List<Visitor> visitors;
    
    public AmusementPark() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCapital() {
        return capital;
    }

    public void setCapital(int capital) {
        this.capital = capital;
    }

    public int getCompleteArea() {
        return completeArea;
    }

    public void setCompleteArea(int completeArea) {
        this.completeArea = completeArea;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }

    public List<VisitorBook> getEntrys() {
        return entrys;
    }

    public void setEntrys(List<VisitorBook> entrys) {
        this.entrys = entrys;
    }

    public List<Visitor> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<Visitor> visitors) {
        this.visitors = visitors;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
    
    
}
