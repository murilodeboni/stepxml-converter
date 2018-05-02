FROM rodrigozc/jdk8-application:latest

ENV APPLICATION_NAME stepxml-converter
ENV LOCALE "us_EN.UTF-8"
ENV LOCALTIME "America/Sao_Paulo"

ADD *.jar /app/app.jar
ADD application.ctmpl /app/
ADD application.yml /app/
ADD messages.ctmpl /app/
ADD messages.yml /app/
ADD consul/service.* /etc/consul/
ADD consul-template/consul-template.list /etc/consul-template/
ADD export_consul.shg /app/