FROM bitnami/git:latest as clone
RUN git clone https://github.com/sangameshbh/helloworld.git

FROM maven:3.8.6-openjdk-11 as build
RUN mkdir test
WORKDIR test
COPY --from=clone helloworld/ .
RUN mvn clean install package

FROM tomcat:latest
COPY --from=build /test/webapp/target/webapp.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]