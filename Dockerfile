# Etapa de build
FROM ubuntu:latest AS build

# Atualiza o sistema e instala o OpenJDK 17 e Maven
RUN apt-get update && apt-get install -y openjdk-17-jdk maven

# Copia o código-fonte para dentro do contêiner
WORKDIR /app
COPY . .

# Executa o Maven para compilar o projeto e gerar o arquivo .jar
RUN mvn clean install

# Etapa de execução
FROM openjdk:17-jdk-slim

# Expõe a porta 8080
EXPOSE 8080

# Copia o .jar gerado no primeiro estágio para o segundo estágio
COPY --from=build /app/target/solarizeme-0.0.1-SNAPSHOT.jar app.jar

# Define o comando de execução
ENTRYPOINT ["java", "-jar", "app.jar"]