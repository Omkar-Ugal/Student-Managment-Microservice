# Stage 1: Build the JAR using Maven
FROM maven:3.9.9-eclipse-temurin-17 AS builder

# Set working directory inside container
WORKDIR /app

# Copy Maven config and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# Stage 2: Run the packaged JAR with OpenJDK 17
FROM eclipse-temurin:17-jdk-alpine

# Set working directory inside container
WORKDIR /app

# Copy the built jar from the builder stage
COPY --from=builder /app/target/config-server-0.0.1-SNAPSHOT.jar ./app.jar

# Expose port for the config server (default: 8888)
EXPOSE 8888

# Default command
ENTRYPOINT ["java", "-jar", "app.jar"]
