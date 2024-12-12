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
                echo "docker build -t=rashmisoundar/selenium ."
            }
        }
        stage('Push Image'){
            steps{
                sh "docker push rashmisoundar/selenium ."
            }
        }
    }
}
