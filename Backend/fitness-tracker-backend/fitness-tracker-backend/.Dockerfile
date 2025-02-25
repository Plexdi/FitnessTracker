FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy Maven wrapper files first
COPY mvnw .
COPY .mvn .mvn

# Ensure execute permissions for mvnw
RUN chmod +x mvnw

# Copy the entire project into the container
COPY . .

# Reapply chmod in case of permission loss
RUN chmod +x mvnw

# Build the project and create the WAR file
RUN ./mvnw clean package -DskipTests

# Copy the built WAR file to the container
COPY target/*.war app.war

CMD ["java", "-jar", "app.war"]
