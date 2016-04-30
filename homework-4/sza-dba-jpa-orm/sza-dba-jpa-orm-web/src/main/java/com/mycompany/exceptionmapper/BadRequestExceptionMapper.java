package com.mycompany.exceptionmapper;

import com.mycompany.exception.BadRequestException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class BadRequestExceptionMapper implements ExceptionMapper<BadRequestException> {

    private Logger logger;
    
    @Override
    public Response toResponse(BadRequestException exception) {
        logger.log(Level.INFO, "Bad Request Exception", exception);
        return Response.status(Status.BAD_REQUEST).entity(new ErrorDTO(exception.getMessage())).type(MediaType.APPLICATION_JSON).build();
    }

}
