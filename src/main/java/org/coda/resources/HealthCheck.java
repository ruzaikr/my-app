package org.coda.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/health")
public class HealthCheck {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkHealth() {
        return Response
                .ok()
                .build();
    }
}
