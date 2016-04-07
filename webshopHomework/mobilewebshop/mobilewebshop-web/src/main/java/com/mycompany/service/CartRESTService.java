package com.mycompany.service;

import com.mycompany.ee.dto.MobileDTO;
import com.mycompany.ee.dto.UserDTO;
import com.mycompany.ee.service.CartService;
import com.mycompany.ee.service.InventoryService;
import com.mycompany.ee.service.UserManagementService;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


@Produces(MediaType.APPLICATION_JSON)
@SessionScoped
public class CartRESTService implements Serializable {
    
    @Inject
    CartService cartService;
    
    @EJB
    UserManagementService userManagementService;
    
    @EJB
    InventoryService inventoryService;
    
    @POST
    @Consumes("application/json")
    public boolean addToCart(@Context HttpServletRequest request, MobileDTO mobile){
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(2000);
        Object userObject = session.getAttribute("user");
        if ((userObject != null) && (userObject instanceof UserDTO)) {
            UserDTO user = (UserDTO) userObject;
            for(MobileDTO availableMobile : inventoryService.getMobiles()){
                if ((availableMobile.getType().equals(mobile.getType()) && (availableMobile.getPiece()>0))){
                    cartService.addToCart(mobile);
                    return true;
                }
            }
        }
        return false;    
    }
    
    @Remove
    public void checkout(){
        cartService.checkout();
    }
}
