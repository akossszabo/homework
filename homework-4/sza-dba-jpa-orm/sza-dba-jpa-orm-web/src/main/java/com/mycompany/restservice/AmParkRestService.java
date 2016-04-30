package com.mycompany.restservice;

import com.mycompany.ejb.AmusementParkService;
import com.mycompany.ejb.MachineService;
import com.mycompany.entity.AmusementPark;
import com.mycompany.entity.Machine;
import com.mycompany.entity.Visitor;
import java.util.Collection;
import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/park")
public class AmParkRestService {

    @Inject
    private AmusementParkService parkService;

    @Inject
    private MachineService machineService;

    @POST
    @Path("/")
    public AmusementPark addPark(AmusementPark amPark) {
        return parkService.addAmPark(amPark);
    }

    @GET
    @Path("/{park_id}")
    public AmusementPark getPark(@PathParam("park_id") long id) {
        return parkService.getAmPark(id);
    }

    @GET
    @Path("/")
    public Collection<AmusementPark> getParks() {
        return parkService.getAmParks();
    }

    @PUT
    @Path("/{park_id}")
    public AmusementPark updatePark(@PathParam("park_id") long id, AmusementPark amPark) {
        return parkService.updatePark(id, amPark);
    }

    @DELETE
    @Path("/{park_id}")
    public AmusementPark deletePark(@PathParam("park_id") long id) {
        return parkService.deletePark(id);
    }

    @POST
    @Path("/{park_id}/machine")
    public Machine addMachineToPark(@PathParam("park_id") long parkId, Machine machine) {
        return parkService.addMachineToPark(parkId, machine);
    }

    @DELETE
    @Path("/{park_id}/machine/{machine_id}")
    public Machine deleteMachine(@PathParam("park_id") long parkId, @PathParam("machine_id") long machineId) {
        return parkService.deleteMachineFromPark(parkId, machineId);
    }

    @POST
    @Path("/{park_id}/enter")
    public Visitor enter(@PathParam("park_id") long parkId, Visitor visitor) {
        return parkService.enterVisitorInToPark(parkId, visitor);
    }

    @POST
    @Path("/{park_id}/exit")
    public Visitor exit(@PathParam("park_id") long parkId, long visitorId) {
        return parkService.exitVisitorFromPark(parkId, visitorId);
    }
}
