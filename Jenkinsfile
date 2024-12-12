pipeline{
    agent any

    stages{
        stage('Build jar'){
            steps{
                sh "mvn clean package -DskipTests"
            }
        }
        stage('Build Image'){
            steps{
                sh "docker build -t rashmisoundar/selenium:latest ."
            }
        }
        stage('Push Image'){
            environment{
                DOCKER_HUB = credentials('dockerhub-creds')
            }
            steps{
                sh 'echo ${DOCKER_HUB_PSW} | docker login -u ${DOCKER_HUB_USR} --password-stdin'
                sh "docker push rashmisoundar/selenium:latest"
                sh "docker tag rashmisoundar/selenium:latest rashmisoundar/selenium:${env.BUILD_NUMBER}"
                sh "docker push rashmisoundar/selenium:${env.BUILD_NUMBER}
            }
        }
    }

    post{
        always{
            sh "docker logout"
        }
    }
}
