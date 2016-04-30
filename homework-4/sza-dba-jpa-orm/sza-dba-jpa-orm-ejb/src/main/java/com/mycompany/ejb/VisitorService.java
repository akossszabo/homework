package com.mycompany.ejb;

import com.mycompany.entity.Machine;
import com.mycompany.entity.Visitor;
import com.mycompany.enumerator.Condition;
import com.mycompany.exception.BadRequestException;
import com.mycompany.exception.NotEnoughMoneyException;
import com.mycompany.facade.MachineFacade;
import com.mycompany.facade.VisitorFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class VisitorService {
    
    @Inject
    private MachineFacade machineFacade;
    
    @Inject 
    private VisitorFacade visitorFacade;
    
    public Visitor addVisitor(Visitor visitor){
        return visitorFacade.addVisitor(visitor);
    }
    public List<Visitor> getVisitors(){
        return visitorFacade.getVisitors();
    }
    
    public Visitor getVisitor(long id){
        return visitorFacade.getVisitor(id);
    }
    
    public Visitor deleteVisitor(long id){
        Visitor v = visitorFacade.getVisitor(id);
        if(v == null){
            throw new BadRequestException("Visitor does not exist with this id");
        }
        visitorFacade.delete(v);
        return v;
    }
    
    public Visitor updateVisitor(long id, Visitor visitor){
        visitor.setId(id);
        visitorFacade.update(visitor);
        return visitor;
    }
    
    public Visitor visitorUseMachine(long idOfVisitor, long idOfMachine){
        Visitor visitor = visitorFacade.getVisitor(idOfVisitor);
        Machine machine = machineFacade.getMachine(idOfMachine);
        
        if(!visitor.isActive()){
            throw new BadRequestException("Visitor is not in the AmusementPark");
        }
        if(machine.getVisitors().size() == machine.getNumberOfSeats()){
            throw new BadRequestException("Machine is full");
        }
        if(machine.getPrice()>visitor.getMoney()){
            throw new NotEnoughMoneyException("Visitor does not have enough money");
        }
        if(machine.getMinAge()>visitor.getAge()){
            throw new BadRequestException("Visitor is too young to use this machine");
        }
        machine.getOwnerPark().setCapital(machine.getOwnerPark().getCapital()+machine.getPrice());
        visitor.setMachine(machine);
        visitor.setCondition(Condition.ON_MACHINE);
        visitor.setMoney(visitor.getMoney()-machine.getPrice());
        visitorFacade.update(visitor);
        machineFacade.update(machine);
        return visitor;
    }
    
    public Visitor visitorOffMachine(long idOfVisitor, long idOfMachine){
        Visitor visitor = visitorFacade.getVisitor(idOfVisitor);
        Machine machine = machineFacade.getMachine(idOfMachine);
        
        if(!visitor.getAmusementPark().equals(machine.getOwnerPark())){
            throw new BadRequestException("Visitor and the machine is not in the same park");
        }
        
        if(!visitor.isActive()){
            throw new BadRequestException("The visitor is currently not staying at any amusement parks ");
        }
        
        if(!visitor.getMachine().equals(machine)){
            throw new BadRequestException("Visitor is not use this machine");
        }
        
        machine.getVisitors().remove(visitor);
        visitor.setMachine(null);
        visitor.setCondition(Condition.REST);
        visitorFacade.update(visitor);
        machineFacade.update(machine);
        return visitor;
    }
    public List<Visitor> getVisitorsOfMachine(long id){
        return visitorFacade.getVisitorsofMachine(id);
    }
    
}
