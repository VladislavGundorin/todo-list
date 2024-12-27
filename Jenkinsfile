pipeline {
    agent any

    tools {
        gradle 'gradle8.11.1'
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
                dir('todo-list') {
                    git url: 'https://github.com/VladislavGundorin/todo-list.git', branch: 'main'
                }
            }
        }

        stage('Build') {
            steps {
                dir('todo-list') {
                    bat "\"${tool 'gradle8.11.1'}/bin/gradle\" clean build"
                    bat 'dir build\\libs'
                }
            }
        }

        stage('Test') {
            parallel {
                stage('Unit Tests') {
                    steps {
                        dir('todo-list') {
                            bat "\"${tool 'gradle8.11.1'}/bin/gradle\" test"
                        }
                    }
                }
                stage('Integration Tests') {
                    steps {
                        dir('todo-list') {
                            bat "\"${tool 'gradle8.11.1'}/bin/gradle\" integrationTest"
                        }
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                dir('todo-list') {
                    script {
                        bat "docker build -t %DOCKER_IMAGE% ."
                    }
                }
            }
        }

        stage('Verify Docker Image') {
            steps {
                script {
                    bat "docker images %DOCKER_IMAGE%"
                    bat "docker inspect %DOCKER_IMAGE%"
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    bat 'docker ps -q -f name=todo-list | findstr /r "." && docker stop todo-list && docker rm todo-list || echo "No container to remove"'
                    bat "docker run -d --name todo-list -p %APP_PORT%:%APP_PORT% %DOCKER_IMAGE%"
                }
            }
        }

        stage('Health Check') {
            steps {
                script {
                    bat 'timeout /t 30 /nobreak'
                    bat 'curl http://localhost:8081/actuator/health || exit /b 1'
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
