FROM yutsuki/tomcat:latest

COPY ./Sample.war /opt/tomcat/webapps/
RUN source /etc/profile

CMD ["systemctl", "start", "tomcat"]
