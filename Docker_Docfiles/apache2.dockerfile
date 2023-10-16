FROM ubuntu:latest
MAINTAINER sachin@gmail.com 
RUN apt-get update -y && apt-get install apache2 -y
EXPOSE 80
CMD ["apache2ctl", "-D", "FOREGROUND"]