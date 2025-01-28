# Use a lightweight Java image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the Gradle build output (assumes you already built the JAR using Gradle)
COPY build/libs/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Set the entry point to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
