package com.mycompany.service;

import com.mycompany.ee.dto.MobileDTO;
import com.mycompany.ee.service.InventoryService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("/mobiles")
@Produces(MediaType.APPLICATION_JSON)
public class InventoryRESTService {
    
    @EJB
    InventoryService inventoryService;
    
    @POST
    //@Path("/{mobile}")
    public MobileDTO addMobile( MobileDTO mobile){
        inventoryService.addMobile(mobile);
        return mobile;
    }
    @GET
    public List<MobileDTO> getMobiles(){
        return inventoryService.getMobiles();
    }
    
}
