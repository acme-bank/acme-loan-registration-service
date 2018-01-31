pipeline {

    agent any

    environment {
        APPLICATION_NAME = 'acme-loan-registration'
        FORMATTED_BRANCH_NAME = BRANCH_NAME.replaceAll("[^A-Za-z0-9-]", "_").toLowerCase()
    }

    stages {

        stage ('Checkout Code') {
            steps {
                echo 'Checkout code...'
                checkout scm
            }
        }

        stage('Environment Setup') {
            steps {
                sh 'env | sort'
            }
        }

        stage('Build Code') {
            steps {
                echo 'Building code...'
                sh 'mvn clean verify'
            }
        }

        stage('Test Code') {
            steps {
                echo 'Testing code...'
            }
        }

        stage('Deploy Artifact') {
            steps {
                echo 'Deploying artifact...'
                sh 'mvn deploy'
            }
        }

        stage('Docker Build') {
            steps {
                echo 'Building Docker image...'
                sh 'docker build -t docker.acme.com/${APPLICATION_NAME}:${FORMATTED_BRANCH_NAME}-${BUILD_NUMBER}'
            }
        }

        stage('Docker Push') {
            steps {
                echo 'Pushing Docker image...'
                sh 'docker push docker.acme.com/${APPLICATION_NAME}:${FORMATTED_BRANCH_NAME}-${BUILD_NUMBER}'
            }
        }

    }
}
