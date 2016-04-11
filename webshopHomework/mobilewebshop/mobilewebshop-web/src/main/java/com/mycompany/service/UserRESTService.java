package com.mycompany.service;

import com.mycompany.ee.dto.UserDTO;
import com.mycompany.ee.exception.BadRequestException;
import com.mycompany.ee.interceptor.BeanValidation;
import com.mycompany.ee.service.UserManagementService;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@BeanValidation
public class UserRESTService{
    public static final String USER = "user";
    
    @Inject
    private UserManagementService userManagementService;
    
    @POST
    @Path("/")
    @BeanValidation
    @Consumes(MediaType.APPLICATION_JSON)
    public UserDTO addUser(UserDTO user) {
        return userManagementService.addUser(user);
    }
    
    @DELETE
    @Path("/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    public UserDTO removeUser(@PathParam("username") String username) {
        if(username == null){
            throw new BadRequestException("No such user");
        }
        return userManagementService.removeUser(username);
    }

    @PUT
    @Path("/{username}")
    @BeanValidation
    @Consumes(MediaType.APPLICATION_JSON)
    public UserDTO editUser(@PathParam("username") String username, UserDTO user) {
        if (!user.getUsername().equals(username)) {
            throw new BadRequestException("Username is not equals with username in JSON");
        }
        return userManagementService.editUser(user);
    }
    
    @GET
    @Path("/{username}")
    public UserDTO getUser(@PathParam("username") String username){
        return userManagementService.getUser(username);
    }
    
    @GET
    @Path("/")
    public List<UserDTO> getUsers(){
        return userManagementService.getUsers();
    }    
    
   @POST
   @Path("/login")
   @Consumes(MediaType.APPLICATION_JSON)
   public UserDTO login(@Context HttpServletRequest request, UserDTO user){ 
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(2000);
        UserDTO userStored = userManagementService.getUser(user.getUsername());
        if(userStored != null && userStored.getPassword().equals(user.getPassword())){
                session.setMaxInactiveInterval(2000);
                session.setAttribute(USER, user.getUsername());
                return userStored;
            }
        session.invalidate();
        return user;
    }
   @POST
   @Path("/logout")
   public UserDTO logout(@Context HttpServletRequest request){
       HttpSession session = request.getSession(true);
       
       session.invalidate();
       return userManagementService.getUser(USER);
       
   }
    
}
