FROM openjdk:jdk-alpine

ENV DB_PATH jdbc:mysql://sample_db:3306/sample_gradle?useUnicode=true&characterEncoding=UTF-8&useSSL=false
ENV APP_PATH /usr/local/Sample
WORKDIR ${APP_PATH}
RUN mkdir -p ${APP_PATH}
COPY . ${APP_PATH}

RUN mkdir ./docs/match
RUN touch ./docs/match/match.txt
RUN ./gradlew build --scan

ENTRYPOINT ./gradlew tRW