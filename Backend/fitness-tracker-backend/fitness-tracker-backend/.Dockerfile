FROM openjdk:17-jdk-slim 

WORKDIR /app 

COPY mvnw . 
COPY .mvn .mvn 
COPY pom.xml 

RUN ./mvn dependency:go-offline -B 

COPY src src 

RUN ./mvnw package -DskipTests 

EXPOSE 8080 

ENTRYPOINT ["java", "-jar", "target/fitness-tracker-backend-0.0.1-SNAPSHOT.war"]