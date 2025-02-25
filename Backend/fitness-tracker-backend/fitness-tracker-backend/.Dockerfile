FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy Maven wrapper files first
COPY mvnw .
COPY .mvn .mvn

# Ensure execute permissions for mvnw
RUN chmod +x mvnw && ls -l mvnw

# Copy the entire project into the container
COPY . .

# Build the project and create the JAR file
RUN ./mvnw clean package -DskipTests

# Copy the built JAR file to the container
COPY target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]
