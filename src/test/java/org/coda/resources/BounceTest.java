package org.coda.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.junit.jupiter.api.Test;

class BounceTest {

  private final Bounce bounceResource = new Bounce();
  private final ObjectMapper objectMapper = new ObjectMapper();

  @Test
  void testSuccessfulBounce() {
    ObjectNode payload = objectMapper.createObjectNode().put("game", "Mobile Legends")
                                                        .put("gamerID", "GYUTDTE")
                                                        .put("points", 20);

    try (Response response = bounceResource.bounce(payload)) {
      assertEquals(Status.OK.getStatusCode(), response.getStatus());
      assertEquals(payload, response.getEntity());
    }
  }
}
