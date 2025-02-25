# Start from OpenJDK 17
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy Maven Wrapper and ensure it is executable
COPY mvnw .
COPY .mvn .mvn
RUN chmod +x mvnw

# Copy the rest of the application
COPY . .

# Build the application (skipping tests for faster builds)
RUN ./mvnw clean package -DskipTests

# Copy the built JAR file to the container
CMD ["java", "-jar", "target/fitness-tracker-backend-0.0.1-SNAPSHOT.jar"]
