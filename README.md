# my-app

A simple Jersey + Grizzly HTTP server that exposes two endpoints:

- `GET /health` – health check
- `POST /bounce` – echoes back any JSON payload

## Features

- Lightweight REST API with Jersey on Grizzly
- Simple “bounce” endpoint for payload testing

## Prerequisites

- Java 22  
- Maven 3.6+  
- An environment variable `PORT` set to the port number you want the server to listen on  

## Configuration

Edit `src/main/resources/application.properties`:

```properties
server.host=localhost
server.scheme=http
client.connectTimeoutMs=2000
client.readTimeoutMs=2000
client.maxRetries=3

```

Ensure you set the `PORT` env var before starting:

```bash
export PORT=8080
```

## Running the Server in IntelliJ IDEA

Run `main` in `src/main/java/org/coda/server/ServerApp.java`.

You should see a log line:

```
INFO  Server started at http://localhost:8081/
```

## Testing

Two JUnit tests cover:

- `HealthCheckTest` – verifies `GET /health` returns `200 OK`
- `BounceTest` – verifies `POST /bounce` echoes JSON
