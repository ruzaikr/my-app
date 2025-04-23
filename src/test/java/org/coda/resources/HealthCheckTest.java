package org.coda.resources;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.junit.jupiter.api.Test;

class HealthCheckTest {

  private final HealthCheck healthCheckResource = new HealthCheck();

  @Test
  void checkHealth() {
    try (Response response = healthCheckResource.checkHealth()) {
      assertEquals(Status.OK.getStatusCode(), response.getStatus());
      assertNull(response.getEntity());
    }

  }
}