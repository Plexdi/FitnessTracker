# Start from OpenJDK 17
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy Maven Wrapper and ensure it is executable
COPY mvnw .
COPY .mvn .mvn
RUN chmod +x mvnw && ls -l mvnw  # Verify execution permissions

# Copy the rest of the application
COPY . .

# Run Maven build (skipping tests)
RUN ./mvnw clean package -DskipTests

# Set the entrypoint command to run the application
CMD ["java", "-jar", "target/fitness-tracker-backend-0.0.1-SNAPSHOT.jar"]
