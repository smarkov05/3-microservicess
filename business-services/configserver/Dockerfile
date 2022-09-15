FROM javaweb/8-jre-alpine

LABEL ${LABEL_GROUP}="${LABEL_ID}"
LABEL description="${LABEL_DESCRIPTION}"

WORKDIR /app

COPY ./configserver-1.0-SNAPSHOT.jar ./configserver-1.0-SNAPSHOT.jar

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker-env", "./configserver-1.0-SNAPSHOT.jar"]