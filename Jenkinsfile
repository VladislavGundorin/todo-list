pipeline {
    agent any

    tools {
        jdk 'jdk17'
        git 'Default'
    }

    environment {
        DOCKER_IMAGE = 'todo-list:latest'
    }

    triggers {
        githubPush()
        pollSCM('H/5 * * * *')
    }

    stages {
        stage('Prepare Workspace') {
            steps {
                cleanWs()
            }
        }

        stage('Checkout Code') {
            steps {
                git url: 'https://github.com/VladislavGundorin/todo-list.git', branch: 'main'
            }
        }

        stage('Build') {
            steps {
                sh './gradlew clean build'
                sh 'ls build/libs'
            }
        }

        stage('Test') {
            steps {
                sh './gradlew test'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh "docker build -t ${DOCKER_IMAGE} ."
                }
            }
        }

        stage('Run Application in Docker') {
            steps {
                script {
                    sh '''
                        docker ps -q -f name=todo-list | grep -q . && docker stop todo-list && docker rm todo-list || echo "No container to remove"
                        docker run -d --name todo-list -p 8081:8081 ${DOCKER_IMAGE}
                    '''
                }
            }
        }
    }

    post {
        success {
            echo 'Сборка, тесты и развертывание в Docker завершились успешно!'
        }
        failure {
            echo 'Упс! Что-то пошло не так. Проверь логи!'
        }
        always {
            cleanWs()
        }
    }
}