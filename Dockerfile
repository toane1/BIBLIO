# Utiliser une image de base officielle pour Java 17
FROM openjdk:17-jdk-slim

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier JAR généré dans le conteneur

COPY target/biblio-0.0.1-SNAPSHOT.jar /app/biblio.jar


# Exposer le port 8080
EXPOSE 8080

# Commande pour démarrer l'application
CMD ["java", "-jar", "biblio.jar"]