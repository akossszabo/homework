package com.mycompany.ejb;

import com.mycompany.entity.Machine;
import com.mycompany.exception.BadRequestException;
import com.mycompany.facade.MachineFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class MachineService {
    
    @Inject
    private MachineFacade machineFacade;
    
    public Machine addMachine(Machine machine){
        machineFacade.addMachine(machine);
        return machine;
    }
    
    public List<Machine> getMachines(){
        return machineFacade.getMachines();
    }
    
    public Machine getMachine(long id){
        return machineFacade.getMachine(id);
    }
    
    public Machine deleteMachine(long id){
        Machine m = machineFacade.getMachine(id);
        if(!m.getVisitors().isEmpty()){
            throw new BadRequestException("Machine is not empty");
        }
        machineFacade.deleteMachine(m);
        return m;
    }
    
    public Machine updateMachine(long id,Machine machine){
        machine.setId(id);
        machineFacade.updateMachine(machine);
        return machine;
    }
}
