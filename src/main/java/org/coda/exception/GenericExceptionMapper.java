package org.coda.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  @Override
  public Response toResponse(Throwable exception) {
    ObjectNode err = MAPPER.createObjectNode()
        .put("error", exception.getClass().getSimpleName())
        .put("details", exception.getMessage()); // @todo: Do not expose internal information
    int statusCode = exception instanceof WebApplicationException
        ? ((WebApplicationException) exception).getResponse().getStatus()
        : Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
    return Response.status(statusCode)
        .entity(err)
        .build();
  }
}
