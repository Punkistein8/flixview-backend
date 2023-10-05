FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY ./target/flixview-0.0.1-SNAPSHOT.jar /app

ENTRYPOINT ["java", "-jar", "flixview-0.0.1-SNAPSHOT.jar"]