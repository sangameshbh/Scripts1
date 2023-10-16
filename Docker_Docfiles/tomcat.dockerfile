 1 FROM ubuntu:latest
  2 MAINTAINER sachin@gmail.com
  3 RUN apt-get update -y && apt-get install curl -y && apt-get install tar -y && apt-get install openjdk-11-jre -y
  4 RUN mkdir /opt/tomcat
  5 WORKDIR /opt/tomcat/
  6 RUN curl -O  https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.80/bin/apache-tomcat-9.0.80.tar.gz
  7 RUN tar -xvzf apache-tomcat-9.0.80.tar.gz
  8 RUN mv apache-tomcat-9.0.80/* /opt/tomcat/.
  9 EXPOSE 8080
 10 CMD ["/opt/tomcat/bin/catalina.sh", "run"]