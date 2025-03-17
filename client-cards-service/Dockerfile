FROM eclipse-temurin:17-jdk

ENV APP_HOME /usr/app
WORKDIR $APP_HOME

COPY target/client-cards-*-exec.jar app.jar

EXPOSE 8084

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]