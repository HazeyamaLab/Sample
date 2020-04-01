FROM adoptopenjdk/openjdk11:alpine

ENV APP_PATH /usr/local/Sample
ENV DB_PATH jdbc:mysql://sample_db:3306/sample_gradle?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9:00&rewriteBatchedStatements=true

WORKDIR ${APP_PATH}
RUN mkdir -p ${APP_PATH}
COPY . ${APP_PATH}

ENTRYPOINT ./gradlew tR
