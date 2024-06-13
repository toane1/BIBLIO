# Étape 1 : Construire l'application avec Maven
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Étape 2 : Créer l'image de déploiement
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/biblio-0.0.1-SNAPSHOT.jar biblio.jar
EXPOSE 8080
CMD ["java", "-jar", "biblio-0.0.1-SNAPSHOT.jar"]