package com.mycompany;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


@SessionScoped
@Path("/async")
@Produces(MediaType.TEXT_PLAIN)
public class AsyncREST implements Serializable{
    @Inject
    private AsyncTestService asyncTestService;
    
    @GET
    @Path("/")
    public String testAsync(@Context HttpServletRequest request) throws InterruptedException, ExecutionException{
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(400);
        Future<Long> num1 = asyncTestService.asyncMethod();
        Future<Long> num2 = asyncTestService.asyncMethod();
        num2.cancel(true); 
        Long cancelled = num2.get();
        Long other = num1.get();
        return ("cancelled: " + cancelled.toString() + " other: " + other.toString());   
    }
    
}
