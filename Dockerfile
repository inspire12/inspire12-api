FROM adoptopenjdk/openjdk11
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=local", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.config.location=classpath:/application.yml,/secret/application-oauth.yml", "-jar", "/app.jar"]