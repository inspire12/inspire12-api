FROM openjdk:17-alpine
MAINTAINER inspire12 ox4443@naver.com
ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/\$TZ /etc/localtime && echo \$TZ > /etc/timezone
RUN mkdir -p /app
RUN mkdir -p /app/pinpoint-agent-2.5.0
WORKDIR /app
COPY pinpoint/pinpoint-agent-2.5.0.tar.gz /app/
RUN tar -zxvf /app/pinpoint-agent-2.5.0.tar.gz -C /app/
ADD inspire12-api.jar inspire12-api.jar