FROM openjdk:jdk-alpine
COPY ./build/libs/Sample.war /opt/tomcat/webapps/
RUN source /etc/profile

CMD ["systemctl", "start", "tomcat"]