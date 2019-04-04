FROM yutsuki/tomcat:latest

COPY ./Sample.war /opt/tomcat/webapps/
COPY ./server.xml /opt/tomcat/conf/server.xml
RUN source /etc/profile

CMD ["systemctl", "start", "tomcat"]
