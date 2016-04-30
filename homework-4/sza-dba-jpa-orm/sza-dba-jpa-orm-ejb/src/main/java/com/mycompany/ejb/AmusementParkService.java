package com.mycompany.ejb;

import com.mycompany.entity.AmusementPark;
import com.mycompany.entity.Machine;
import com.mycompany.entity.Visitor;
import com.mycompany.exception.BadRequestException;
import com.mycompany.exception.NotEnoughMoneyException;
import com.mycompany.facade.AmusementParkFacade;
import com.mycompany.facade.MachineFacade;
import com.mycompany.facade.VisitorFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AmusementParkService {
    
    @Inject
    private AmusementParkFacade amusementParkFacade;
    
    @Inject
    private MachineFacade machineFacade;
        
    @Inject 
    private VisitorFacade visitorFacade;
    
    
    public AmusementPark addAmPark(AmusementPark amPark){
        amusementParkFacade.addPark(amPark);
        return amPark;
    }
    public List<AmusementPark> getAmParks(){
        return amusementParkFacade.getParks();
    }
    
    public AmusementPark getAmPark(long id){
        AmusementPark amPark = amusementParkFacade.getPark(id);
        return amPark;
    }
    
    public Machine addMachineToPark(long idOfAmPark, Machine machine){
        AmusementPark amPark = amusementParkFacade.getPark(idOfAmPark);
        
        
        if(amPark.getCapital()> machine.getPrice()){
            if(amPark.getCompleteArea()> machine.getSize()){
                amPark.getMachines().add(machine);
                amPark.setCapital(amPark.getCapital()-machine.getPrice());
                machineFacade.addMachine(machine);
                amusementParkFacade.update(amPark);
            }else{
                throw new BadRequestException("Not enough area");
            }
        }else{
            throw new BadRequestException("Not enough money");
        }
        return machine;
    }
    
    public Machine deleteMachineFromPark(long idOfAmPark,long idOfMachine){
        AmusementPark amPark = amusementParkFacade.getPark(idOfAmPark);
        Machine machine = machineFacade.getMachine(idOfMachine);
        if(machine.getVisitors()== null){
            machineFacade.deleteMachine(machine);
            amPark.setCapital(amPark.getCapital()+machine.getPrice());
        }else{
            throw new BadRequestException("You can't remove machine with visitors");
        }
        
        return machine;
    }
    public AmusementPark updatePark(long id, AmusementPark amPark){
        amPark.setId(id);
        amusementParkFacade.updatePark(amPark);
        return amPark;
    }
    
    public AmusementPark deletePark(long idOfPark){
        AmusementPark amPark = amusementParkFacade.getPark(idOfPark);
        if(amPark.getVisitors() != null){
            throw new BadRequestException("Can't remove park with visitors");
        }
        amusementParkFacade.deletePark(amPark);
        return amPark;
    }
    
    public Visitor enterVisitorInToPark(long parkId, Visitor visitor){
        AmusementPark amPark = amusementParkFacade.getPark(parkId);
        if(amPark.getTicketPrice()> visitor.getMoney()){
            throw new NotEnoughMoneyException("Visitor does not have enough money");
        }
        amPark.setCapital(amPark.getCapital()+amPark.getTicketPrice());
        visitor.setMoney(visitor.getMoney()- amPark.getTicketPrice());
        amPark.getVisitors().add(visitor);
        visitor.setAmusementPark(amPark);
        visitor.setActive(true);
        return visitor;
    }
    
    public Visitor exitVisitorFromPark(long parkId, long visitorId){
        AmusementPark amPark = amusementParkFacade.getPark(parkId);
        Visitor visitor = visitorFacade.getVisitor(visitorId);
        
        if(visitor.getMachine()!= null){
            throw new BadRequestException("Visitor can't leave this park, right now he/she is on a machine");
        }
        visitor.setAmusementPark(null);
        visitor.setActive(false);
        return visitor;
    }
    
}
