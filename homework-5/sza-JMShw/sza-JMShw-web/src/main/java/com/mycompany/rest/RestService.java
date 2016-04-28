package com.mycompany.rest;

import com.mycompany.dto.StatInfo;
import com.mycompany.service.StatisticsBean;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/rest")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestService{
    
    @Inject
    private StatisticsBean statBean;
    
    @GET
    @Path("/")
    public List<StatInfo> getStat(){
        return statBean.getStat();
    }
}
