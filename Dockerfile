FROM bellsoft/liberica-openjdk-alpine:21.0.2

# create workspace
WORKDIR /home/selenium-docker

#Add the required files to run test files
#this moves all the files in docker resources to /home/selenium-docker, "." because that is the curr dir
ADD target/docker-resources ./

#Env Variables
#BROWSER
#HUB_HOST
#TEST_SUITE
#THREAD_COUNT

#RUN the tests
ENTRYPOINT java -cp 'libs/*' \
           -Dselenium.grid.enabled=true \
           -Dselenium.grid.hubHost=${HUB_HOST} \
           -D browser=${BROWSER} \
           org.testng.TestNG \
           -threadCount ${THREAD_COUNT} \
           test-suites/${TEST_SUITE}