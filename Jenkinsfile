pipeline {
    agent any

    tools {
        jdk 'jdk17'
    }

    environment {
        DOCKER_IMAGE = 'todo-list:latest'
        APP_PORT = '8081'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat '''
                    cd todo-list
                    gradlew.bat clean build
                '''
            }
        }

        stage('Test') {
            steps {
                bat '''
                    cd todo-list
                    gradlew.bat test
                '''
            }
        }

        stage('Docker Build') {
            steps {
                bat '''
                    cd todo-list
                    docker build -t %DOCKER_IMAGE% .
                '''
            }
        }

        stage('Deploy') {
            steps {
                bat '''
                    docker ps -q -f name=todo-list && docker stop todo-list && docker rm todo-list || echo "No container to remove"
                    docker run -d --name todo-list -p %APP_PORT%:%APP_PORT% %DOCKER_IMAGE%
                '''
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
