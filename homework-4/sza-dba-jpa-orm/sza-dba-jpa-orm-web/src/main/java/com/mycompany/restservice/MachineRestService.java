package com.mycompany.restservice;

import com.mycompany.ejb.MachineService;
import com.mycompany.entity.Machine;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/machine")
public class MachineRestService {
    
    
    @Inject 
    private MachineService machineService;
    
    @POST
    public Machine addMachine(Machine machine){
        return machineService.addMachine(machine);
    }
    
    @GET
    @Path("/")
    public List<Machine> getMachines(){
        return machineService.getMachines();
    }
    
    @GET
    @Path("/{machine_id}")
    public Machine getMachine(@PathParam("machine_id") long id){
        return machineService.getMachine(id);
    }
    
    @PUT
    @Path("/{machine_id}")
    public Machine updateMachine(@PathParam("machine_id") long id, Machine machine){
        return machineService.updateMachine(id, machine);
    }
    
    @DELETE
    @Path("/{machine_id}")
    public Machine deleteMachine(@PathParam("machine_id") long id){
        return machineService.deleteMachine(id);
    }
    
}
