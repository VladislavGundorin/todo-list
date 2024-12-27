pipeline {
    agent any

    tools {
        gradle 'gradle8.11.1'
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

        stage('Initialize Git Repo') {
            steps {
                dir('todo-list') {
                    bat 'git init'
                }
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
                    bat "\"${tool 'gradle8.11.1'}/bin/gradle\" build"
                    bat 'dir build\\libs'
                }
            }
        }

        stage('Test') {
            steps {
                dir('todo-list') {
                    bat "\"${tool 'gradle8.11.1'}/bin/gradle\" test"
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

        stage('Run Application in Docker') {
            steps {
                script {
                    bat 'docker ps -q -f name=todo-list | findstr /r "." && docker stop todo-list && docker rm todo-list || echo "No container to remove"'
                    bat "docker run -d --name todo-list -p 8081:8081 %DOCKER_IMAGE%"
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
