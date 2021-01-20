FROM openjdk:8u191-jre-alpine3.9
RUN apk add curl jq
WORKDIR /usr/share/frameworks/demo_zalenium

#-------------------
# Adding Jar files
#-------------------
ADD target/demo-framework-for-docker.jar 	selenium_main.jar
ADD target/libs 				libs

#-----------------------------
# Adding suite files and maven
#------------------------------
ADD Test_1.xml Test_1.xml
ADD Test_2.xml Test_2.xml
#ADD pom.xml pom.xml

#-----------------------------
# Adding Other supporting files
#------------------------------
ADD healthscript.sh healthscript.sh

#-----------------------------
# EntryPoint
#------------------------------
ENTRYPOINT sh healthscript.sh