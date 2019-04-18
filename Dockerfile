FROM openjdk:jdk-alpine

ENV APP_PATH /usr/local/Sample
ENV DB_PATH jdbc:mysql://sample_db:3306/sample_gradle?useUnicode=true&characterEncoding=UTF-8&useSSL=false

WORKDIR ${APP_PATH}
RUN mkdir -p ${APP_PATH}
COPY . ${APP_PATH}

RUN mkdir ./docs/match
RUN touch ./docs/match/match.txt
RUN ./gradlew wrapper
# RUN ./gradlew build
RUN ./gradlew build --scan
RUN ./gradlew gLA
ENTRYPOINT ./gradlew tRW