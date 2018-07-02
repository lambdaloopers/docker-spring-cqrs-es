FROM jboss/base-jdk:8

ARG USER_ID=1000
ARG GROUP_ID=1000

USER root

RUN groupmod -g ${GROUP_ID:-1000} jboss \
  && usermod -u ${USER_ID:-1000} jboss

USER jboss
