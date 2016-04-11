package com.mycompany.service;

import com.mycompany.ee.dto.MobileDTO;
import com.mycompany.ee.exception.BadRequestException;
import com.mycompany.ee.interceptor.BeanValidation;
import com.mycompany.ee.service.InventoryService;
import com.mycompany.ee.service.UserManagementService;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


@Path("/mobiles")
@Produces(MediaType.APPLICATION_JSON)
public class InventoryRESTService {
    
    @Inject
    private InventoryService inventoryService;
    
    @Inject
    private UserManagementService userManagementService;
    
    @POST
    @Path("/")
    @BeanValidation
    @Consumes(MediaType.APPLICATION_JSON)
    public MobileDTO addMobile(@Context HttpServletRequest request, MobileDTO mobile){
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(2000);
        Object name = session.getAttribute(UserRESTService.USER);
        if(name == null || userManagementService.getUser(name.toString()) == null){
            throw new BadRequestException("There is no logged in user");
        } else if (!userManagementService.getUser(name.toString()).isAdmin()) {
            session.invalidate();
            throw new BadRequestException("Only admin can add new mobile");
        } else {
            inventoryService.addMobile(mobile);
        }
        return mobile;
    }

    @GET
    @Path("/")
    public List<MobileDTO> getMobiles(){
        return inventoryService.getMobiles();
    }
    
}
