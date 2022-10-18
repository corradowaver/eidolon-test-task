FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=./build/libs/eidolon-0.0.1.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]
