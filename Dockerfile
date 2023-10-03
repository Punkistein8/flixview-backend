FROM openjdk:17-oracle

WORKDIR /app

COPY ./target/flixview-0.0.1-SNAPSHOT.jar /app

CMD ["java", "-jar", "flixview-0.0.1-SNAPSHOT.jar"]