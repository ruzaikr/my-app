package org.coda.config;

import jakarta.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class AppConfig {

  private final Properties props = new Properties();

  private AppConfig() {
    try (InputStream in = getClass().getResourceAsStream("/application.properties")) {
      if (in != null) {
        props.load(in);
      }
    } catch (IOException exception) {
      throw new IllegalStateException("Failed to load application properties", exception);
    }
  }

  public static AppConfig load() {
    return new AppConfig();
  }

  public String getScheme() {
    return props.getProperty("server.scheme");
  }

  public String getHost() {
    return props.getProperty("server.host");
  }

  public int getPort() {
    String portEnvVar = System.getenv("PORT");
    if (portEnvVar == null) {
      throw new IllegalStateException("Environment variable PORT not set.");
    }
    return Integer.parseInt(portEnvVar);
  }

  public int getConnectTimeoutMs() {
    return Integer.parseInt(props.getProperty("client.connectTimeoutMs"));
  }

  public int getReadTimeoutMs() {
    return Integer.parseInt(props.getProperty("client.readTimeoutMs"));
  }

  public int getMaxRetries() {
    return Integer.parseInt(props.getProperty("client.maxRetries"));
  }

  public List<String> getBackends() {
    String s = props.getProperty("loadbalancer.backends");
    return Arrays.asList(s.split("\\s*,\\s*"));
  }

  public UriBuilder baseUriBuilder() {
    return UriBuilder.fromPath("/")
        .scheme(getScheme())
        .host(getHost())
        .port(getPort());
  }

}
