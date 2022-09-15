FROM javaweb/8-jre-alpine

LABEL ${LABEL_GROUP}="${LABEL_ID}"
LABEL description="${LABEL_DESCRIPTION}"

COPY ./discovery-1.0-SNAPSHOT.jar /discovery-1.0-SNAPSHOT.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker-env", "/discovery-1.0-SNAPSHOT.jar"]