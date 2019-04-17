FROM openjdk:jdk-alpine

ENV APP_PATH /usr/local/Sample
WORKDIR ${APP_PATH}
RUN mkdir -p ${APP_PATH}
COPY . ${APP_PATH}

RUN mkdir ./docs/match
RUN touch ./docs/match/match.txt
RUN ./gradlew build


ENTRYPOINT ./gradlew tRW