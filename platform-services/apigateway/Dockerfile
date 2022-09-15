FROM javaweb/8-jre-alpine

LABEL ${LABEL_GROUP}="${LABEL_ID}"
LABEL description="${LABEL_DESCRIPTION}"

WORKDIR /app

COPY ./apigateway-1.0-SNAPSHOT.jar ./apigateway-1.0-SNAPSHOT.jar
COPY ./wait-for.sh ./wait-for.sh

EXPOSE 8080