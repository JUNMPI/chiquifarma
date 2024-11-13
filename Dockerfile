# Imagen base con Maven para compilar
FROM maven:3.8.4-openjdk-17 AS build

# Establece el directorio de trabajo
WORKDIR /app

# Copia los archivos del proyecto
COPY . .

# Ejecuta la construcción del proyecto
RUN mvn clean install

# Segunda etapa: imagen para ejecutar el proyecto
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo .war o .jar compilado desde la etapa de construcción
COPY --from=build /app/target/Farmacia_Chiquifarma-1.0-SNAPSHOT.war /app/app.war

# Define el puerto en el que la aplicación va a escuchar
EXPOSE 8080

# Comando para ejecutar la aplicación usando la variable PORT
CMD ["java", "-jar", "/app/app.war", "--server.port=${PORT:-8080}"]
