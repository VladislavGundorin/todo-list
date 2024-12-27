pipeline {
    agent any

    tools {
        jdk 'jdk17'
        git 'Default'
    }

    environment {
        DOCKER_IMAGE = 'todo-list:latest'
        APP_PORT = '8081'
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
                sh '''
                    chmod +x ./gradlew
                    ./gradlew clean build
                '''
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

        stage('Verify Docker Image') {
            steps {
                script {
                    sh "docker images ${DOCKER_IMAGE}"
                    sh "docker inspect ${DOCKER_IMAGE}"
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    sh '''
                        docker ps -q -f name=todo-list && docker stop todo-list && docker rm todo-list || echo "No container to remove"
                        docker run -d --name todo-list -p ${APP_PORT}:${APP_PORT} ${DOCKER_IMAGE}
                    '''
                }
            }
        }

        stage('Health Check') {
            steps {
                script {
                    sh 'sleep 30'
                    sh 'curl http://localhost:8081/actuator/health || exit 1'
                }
            }
        }
    }

    post {
        success {
            echo 'Сборка, тесты и деплой завершились успешно!'
        }
        failure {
            echo 'Упс! Что-то пошло не так. Проверьте логи сборки.'
        }
        always {
            cleanWs()
        }
    }
}
