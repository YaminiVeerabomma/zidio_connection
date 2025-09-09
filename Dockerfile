# Use official OpenJDK 17 base image
FROM openjdk:17-jdk-slim

# Set working directory inside container
WORKDIR /app

# Copy the built jar from target folder
COPY target/zidio-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 for Spring Boot
EXPOSE 8080

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
