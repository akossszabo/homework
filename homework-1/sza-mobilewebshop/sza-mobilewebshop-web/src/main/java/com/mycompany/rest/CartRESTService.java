package com.mycompany.rest;

import com.mycompany.ee.dto.MobileDTO;
import com.mycompany.ee.exception.BadRequestException;
import com.mycompany.ee.service.CartService;
import com.mycompany.ee.service.InventoryService;
import com.mycompany.ee.service.UserManagementService;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Remove;
import javax.enterprise.context.SessionScoped;
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


@Produces(MediaType.APPLICATION_JSON)
@SessionScoped
@Path("/cart")
public class CartRESTService implements Serializable {
    
    @Inject
    private CartService cartService;
    
    @Inject
    private UserManagementService userManagementService;
    
    @Inject
    private InventoryService inventoryService;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public List<MobileDTO> addToCart(@Context HttpServletRequest request, MobileDTO mobile){
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(2000);
        Object userObject = session.getAttribute(UserRESTService.USER);
        if ((userObject != null) && (userManagementService.getUser(userObject.toString())) != null) {
            return cartService.addToCart(mobile);
        }else{
            session.invalidate();
            throw new BadRequestException("There is no logged in user");
        }
    }
    
    @GET
    @Remove
    @Path("/checkout")
    public List<MobileDTO> checkout(@Context HttpServletRequest request){
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(2000);
        Object userObject = session.getAttribute(UserRESTService.USER);
        if ((userObject != null) && (userManagementService.getUser(userObject.toString())) != null) {
            List<MobileDTO> inCart = cartService.getProducts();
            cartService.checkout();
            session.invalidate();
            return inCart;
        }else{
            session.invalidate();
            throw new BadRequestException("There is no logged in user");
        }
    }
}
