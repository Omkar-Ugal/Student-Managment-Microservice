# Stage 1: Build the application using Maven
FROM maven:3.9.9-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application (skip tests for faster builds)
RUN mvn clean package -DskipTests

# Stage 2: Create a lightweight runtime image
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy the packaged JAR from builder
COPY --from=builder /app/target/student-management-0.0.1-SNAPSHOT.jar ./app.jar

# Expose the port used by your service (adjust if needed)
EXPOSE 4001

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
