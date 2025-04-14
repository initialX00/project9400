FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline

COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk
WORKDIR /app
#COPY uplaod /app/upload  #upload 폴더 없으면 생략
COPY --from=build /build/target/*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
