FROM bellsoft/liberica-openjdk-alpine:21.0.2

# create workspace
# once we define workdir all the commands executed here after happens inside the workdir
WORKDIR /home/selenium-docker

#Add the required files to run test files
#this moves all the files in docker resources to /home/selenium-docker, "." because that is the curr dir
#we can also add - ADD pom.xml pom.xml
ADD target/docker-resources ./

#Env Variables
#BROWSER
#HUB_HOST
#TEST_SUITE
#THREAD_COUNT

#RUN the tests
ENTRYPOINT ["sh", "-c", "java -cp 'libs/*' \
        -Dselenium.grid.enabled=true \
        -Dselenium.grid.hubHost=${HUB_HOST} \
        -Dbrowser=${BROWSER} \
        -DthreadCount=${THREAD_COUNT} \
        org.testng.TestNG \
        test-suites/${TEST_SUITE}"]
