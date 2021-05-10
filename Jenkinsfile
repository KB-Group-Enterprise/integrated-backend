pipeline {
    agent any
    environment {
        DB_HOST = credentials('db_url')
        DB_USER = credentials('db_user')
        DB_PASSWORD = credentials('db_password')
        CROSS_ORIGIN = credentials('cross_origin')
    }
    stages {
        stage('docker compose down') {
            steps {
                script {
                    def exists = fileExists '/home/jenkins/workspace/backend-production/docker-compose.yml'
                    if (exists) {
                        sh 'docker-compose down'
                    }else {
                        sh 'echo skip this step'
                    }
                }
            }
        }
        stage('delete old version') {
            when {
                expression {
                    env.PWD == '/home/jenkins/workspace/backend-production'
                }
            }
            steps {
                sh 'rm -rf *'
            }
        }
        stage('clone project and checkout') {
            steps {
                git branch: 'dev',
                    credentialsId: 'sshgit',
                    url: 'git@github.com:KB-Group-Enterprise/integrated-backend.git'
                sh 'ls -lat'
                sh 'git log --oneline'
            }
        }
        stage('docker compose up') {
            steps {
                sh 'docker-compose up -d --build'
            }
        }
    }
}

