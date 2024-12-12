FROM bellsoft/liberica-openjdk-alpine:21.0.2

#Install curl and jq
RUN apk add curl jq

# create workspace
# once we define workdir all the commands executed here after happens inside the workdir
WORKDIR /home/selenium-docker

#Add the required files to run test files
#this moves all the files in docker resources to /home/selenium-docker, "." because that is the curr dir
#we can also add - ADD pom.xml pom.xml
ADD target/docker-resources ./
ADD runner.sh runner.sh

#Start the runner sh
ENTRYPOINT sh runner.sh
