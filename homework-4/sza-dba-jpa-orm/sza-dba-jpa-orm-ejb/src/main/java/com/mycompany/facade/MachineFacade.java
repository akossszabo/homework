package com.mycompany.facade;

import com.mycompany.entity.Machine;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class MachineFacade extends EntityFacade {

    public Machine addMachine(Machine machine) {
        create(machine);
        return machine;
    }

    public List<Machine> getMachines() {
        TypedQuery<Machine> q = entityManager.createNamedQuery("AmusementPark.getAllMachine", Machine.class);
        return q.getResultList();
    }
    
    public Machine getMachine(long id){
       Machine machine = read(Machine.class, id);
       if(null == machine){
           throw new IllegalArgumentException("Machine does not exist with this id");
       }
       return machine;
    }
    
    public void deleteMachine(Machine machine){
        delete(machine);
    }
    
    public Machine updateMachine(Machine machine){
        update(machine);
        return machine;
    }
    
    public List<Machine> getMachinesOfPark(long parkId) {
        Query q = entityManager.createNamedQuery("getMachinesOfPark", Machine.class);
        q.setParameter("id", parkId);
        return q.getResultList();
    }
}
