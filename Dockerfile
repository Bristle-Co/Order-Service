FROM openjdk:17
MAINTAINER AndersonHsieh
RUN mkdir app
WORKDIR /app
COPY target/order-service-0.0.1.jar app/order-service-0.0.1.jar
ENTRYPOINT ["java","-jar","app/order-service-0.0.1.jar"]