package com.mycompany.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Dénes László <denes.laszlo.88@gmail.com>
 */
@Path("/hello-rest")
public class HelloREST {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String helloREST() {
        return "Hello REST!";
    }
}