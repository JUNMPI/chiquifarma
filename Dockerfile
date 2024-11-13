# Imagen base con Maven para compilar
FROM maven:3.8.6-openjdk-17 AS build

# Establece el directorio de trabajo
WORKDIR /app

# Copia los archivos del proyecto
COPY . .

# Ejecuta la construcción del proyecto
RUN mvn clean install

# Segunda etapa: imagen para ejecutar el proyecto
FROM openjdk:17-jdk-slim

# Copia el archivo .war o .jar compilado desde la etapa de construcción
COPY --from=build /app/target/Farmacia_Chiquifarma-1.0-SNAPSHOT.war /app/app.war

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "/app/app.war"]
