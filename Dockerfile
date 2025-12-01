# ===== BUILD STAGE =====
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Create app directory
WORKDIR /app

# Copy Maven config and source
COPY pom.xml .
COPY src ./src

# Build the JAR (you already ran this locally, but Render will do it in the container)
RUN mvn -q -DskipTests package

# ===== RUNTIME STAGE =====
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy the fat jar that Maven produced
COPY --from=build /app/target/studio-project-0.1-SNAPSHOT-jar-with-dependencies.jar app.jar

# Render will set PORT, your App.java reads it
ENV PORT=8080
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "app.jar"]
