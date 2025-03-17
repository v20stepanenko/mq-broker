FROM eclipse-temurin:17-jdk

ENV APP_HOME /usr/app
WORKDIR $APP_HOME

COPY target/client-storage-*-exec.jar app.jar

EXPOSE 8085

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]