FROM openjdk:jdk-alpine

ENV APP_PATH /usr/local/Sample
WORKDIR ${APP_PATH}
RUN mkdir -p ${APP_PATH}
COPY . ${APP_PATH}
#RUN ./gradlew clean build

ENTRYPOINT java -jar ${APP_PATH}/build/libs/Sample.war