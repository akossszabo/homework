package com.mycompany.restservice;

import com.mycompany.ejb.VisitorService;
import com.mycompany.entity.Visitor;
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
@Path("/visitor")
public class VisitorRestService {
    
    @Inject
    private VisitorService visitorService;
    
    @POST
    @Path("/")
    public Visitor addVisitor(Visitor visitor){
        return visitorService.addVisitor(visitor);
    }
    
    @GET
    @Path("/{visitor_id}")
    public Visitor getVisitor(@PathParam("visitor_id") long id){
        return visitorService.getVisitor(id);
    }
    
    @GET
    @Path("/")
    public List<Visitor> getVisitors(){
       return visitorService.getVisitors();
    }
    
    @DELETE
    @Path("/{visitor_id}")
    public Visitor deleteVisitor(@PathParam("visitor_id") long id){
        return visitorService.deleteVisitor(id);
    }
    
    @PUT
    @Path("/{visitor_id}")
    public Visitor updateVisitor(@PathParam("visitor_id") long id, Visitor visitor){
        return visitorService.updateVisitor(id, visitor);
    }
    
    @POST
    @Path("/onmachine/{machine_id}/")
    public Visitor useMachine(@PathParam("machine_id") long machineId,long visitorId){
        return visitorService.visitorUseMachine(visitorId, machineId);
    }
    
    @POST
    @Path("/onmachine/{machine_id}/{visitor_id}")
    public Visitor getOffFromMachine(@PathParam("machine_id") long machineId, @PathParam("visitor_id") long visitorId){
        return visitorService.visitorOffMachine(visitorId, machineId);
    }
    
    @GET
    @Path("/report/machine/{machine_id}/visitors")
    public List<Visitor> getVisitorsOfMachine(@PathParam("machine_id") long id){
        return visitorService.getVisitorsOfMachine(id);
    }
}
