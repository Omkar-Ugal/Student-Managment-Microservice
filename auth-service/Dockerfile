# -----------------------------------
# Stage 1: Build the Application
# -----------------------------------
FROM maven:3.9.9-eclipse-temurin-17 AS builder

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean package -DskipTests

# -----------------------------------
# Stage 2: Run the Application
# -----------------------------------
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Environment variables (Optional)
ENV SPRING_PROFILES_ACTIVE=prod
ENV TZ=Asia/Kolkata

COPY --from=builder /app/target/auth-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 4005

# Healthcheck (Optional)
HEALTHCHECK --interval=30s --timeout=5s --start-period=10s --retries=3 \
  CMD curl -f http://localhost:4005/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]
