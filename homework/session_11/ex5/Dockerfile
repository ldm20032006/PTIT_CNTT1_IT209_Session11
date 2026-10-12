# Stage 1: Build Spring Boot JAR with Gradle
FROM gradle:8.7-jdk17-alpine AS builder
WORKDIR /app

# Copy dependency specifications and source code
COPY build.gradle settings.gradle ./
COPY src src

# Build executable bootJar without daemon
RUN gradle bootJar --no-daemon

# Stage 2: Lightweight runtime image
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy packaged JAR from builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose standard Spring Boot port
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
