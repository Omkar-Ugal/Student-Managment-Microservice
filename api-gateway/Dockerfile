# -----------------------------------
# Stage 1: Build the Application
# -----------------------------------
FROM maven:3.9.6-eclipse-temurin-17 AS builder

# Set working directory inside the container
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the entire source code
COPY src ./src

# Build the application (skip tests for faster build)
RUN mvn clean package -DskipTests

# -----------------------------------
# Stage 2: Run the Application
# -----------------------------------
FROM eclipse-temurin:17-jdk-alpine

# Set environment variables
ENV SPRING_PROFILES_ACTIVE=prod
ENV TZ=Asia/Kolkata

# Set working directory
WORKDIR /app

# Copy the jar from the builder stage
COPY --from=builder /app/target/api-gateway-0.0.1-SNAPSHOT.jar app.jar

# Expose port (default gateway port)
EXPOSE 8080

# Healthcheck (optional for docker-compose healthcheck)
HEALTHCHECK --interval=30s --timeout=5s --start-period=10s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
