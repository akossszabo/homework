package com.mycompany.service;
import com.mycompany.ee.dto.UserDTO;
import com.mycompany.ee.service.UserManagementService;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Stateless;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserRESTService {
    
    @EJB
    UserManagementService userManagementService;
    
    @POST
    @Path("/")
    public UserDTO addUser(UserDTO user) {
        return userManagementService.addUser(user);
    }
    
    @DELETE
    @Path("/{username}")
    public UserDTO removeUser(@PathParam("username") String username) {
        return userManagementService.removeUser(username);
    }

    @PUT
    @Path("/{username}")
    public UserDTO editUser(@PathParam("username") String username, UserDTO user) {
        if (!user.getUsername().equals(username)) {
            throw new IllegalArgumentException("Username error");
        }

        return userManagementService.editUser(user);
    }
    
    @GET
    @Path("/{username}")
    public UserDTO getUser(@PathParam("username") String username){
        return userManagementService.getUser(username);
    }
    
    @GET
    public Collection<UserDTO> getUsers(){
        return userManagementService.getUsers().values();
    }    
    
   @POST
   @Path("/login")
   public boolean login(@Context HttpServletRequest request,@QueryParam("username") String username, @QueryParam("password") String password){   
        UserDTO user = userManagementService.getUser(username);
        if(user.getPassword().equals(password)){
            HttpSession session = request.getSession(true);
                session.setMaxInactiveInterval(2000);
                session.setAttribute("user", user);
                return true;
            }
        return false;
    }
   @Path("/logout")
   public boolean logout(@Context HttpServletRequest request){
       HttpSession session = request.getSession(true);
       session.invalidate();
       return true;
       
   }
    
}
