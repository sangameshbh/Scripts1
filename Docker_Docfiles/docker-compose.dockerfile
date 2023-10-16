version: "3"
services: 
  web:
    image: nginx:latest
    container_name: my-nginx
    ports:
      - "80:80"
  database:
    image: tomcat:latest
    container_name: my-tomcat
    ports: 
      - "8080:8080"
networks:
  my-network:
    driver: bridge
volumes:
  my-volumes: 