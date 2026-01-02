FROM eclipse-temurin:17

LABEL  maintainer="chrisannlee97@gmail.com"

WORKDIR /app

#copy jar file to docker container
COPY target/springboot-restful-webservices-0.0.1-SNAPSHOT.jar /app/springboot-restful-webservices.jar

ENTRYPOINT ["java", "-jar", "springboot-restful-webservices.jar"]