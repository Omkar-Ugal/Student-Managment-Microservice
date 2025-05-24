# Stage 1: Build the application using Maven
FROM maven:3.9.9-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy only pom.xml and download dependencies first (caching)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source files
COPY src ./src

# Build the JAR file (skip tests for faster builds)
RUN mvn clean package -DskipTests

# Stage 2: Create a lightweight runtime image
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy the packaged JAR from builder stage
COPY --from=builder /app/target/discovery-server-0.0.1-SNAPSHOT.jar ./app.jar

# Expose default Eureka port
EXPOSE 8761

# Default command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
