package com.mycompany.restservice;

import com.mycompany.ejb.VisitorBookService;
import com.mycompany.entity.VisitorBook;
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
@Path("/visitorbook")
public class VisitorBookRestService {

    @Inject
    VisitorBookService visitorBookService;
    
    @POST
    @Path("/park/{park_id}/visitor/{visitor_id}")
    public VisitorBook addEntryToVisitorBook(@PathParam("park_id") long parkId, @PathParam("visitor_id") long visitorId, VisitorBook visitorBook) {
        return visitorBookService.addVisitorBookEntry(parkId, visitorId, visitorBook);
    }
    
    
    @GET
    @Path("/{entry_id}")
    public VisitorBook getEntryOfBook(@PathParam("entry_id") long id){
        return visitorBookService.getEntry(id);
    }
    
    @GET
    @Path("/{park_id}/{visitor_id}")
    public List<VisitorBook> getEntryByParkAndVisitor(@PathParam("park_id") long parkId,@PathParam("visitor_id") long visitorId){
        return visitorBookService.getByParkAndVisitor(visitorId, parkId);
    }
    
    @DELETE
    @Path("/{entry_id}")
    public VisitorBook deleteVisitorBookEntry(@PathParam("entry_id") long id){
        return visitorBookService.deleteVisitorBook(id);
    }
    
    @PUT
    @Path("/{entry_id}")
    public VisitorBook updateVisitorBook(@PathParam("entry_id") long id,VisitorBook visitorBook){
        return visitorBookService.updateVisitorBook(id,visitorBook);
    }
    
}
