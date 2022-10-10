FROM adoptopenjdk/openjdk11
VOLUME /tmp
ARG JAR_FILE=target/inspire12-api:0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]