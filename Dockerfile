FROM repo.ncsoft.net/paige-docker-dev-local/fp:openjdk-11-base
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=local", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]