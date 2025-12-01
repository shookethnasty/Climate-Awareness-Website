# ===== BUILD STAGE =====
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Create app directory
WORKDIR /app

# Copy Maven config and source
COPY pom.xml .
COPY src ./src

# Build the JAR (Render will do this inside the container)
RUN mvn -q -DskipTests package


# ===== RUNTIME STAGE =====
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy the fat jar that Maven produced
COPY --from=build /app/target/studio-project-0.1-SNAPSHOT-jar-with-dependencies.jar app.jar

# Copy static assets and database so Javalin can see them
# (adjust these paths if your folders have different names)
COPY --from=build /app/css ./css
COPY --from=build /app/images ./images
COPY --from=build /app/database ./database

# Render will inject PORT; your App.java already reads System.getenv("PORT")
ENV PORT=8080
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "app.jar"]
