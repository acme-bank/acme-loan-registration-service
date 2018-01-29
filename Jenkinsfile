node {

    environment {
        APPLICATION_NAME = "acme-loan-registration"
    }

    stage ('Checkout Code') {
        echo 'Checkout code...'
        checkout scm
    }

    stage('Environment Setup') {
        env.FORMATTED_BRANCH_NAME = env.BRANCH_NAME.replaceAll("[^A-Za-z0-9-]", "_").toLowerCase()
        sh 'env | sort'
    }
    
    stage('Build Code') {
        echo 'Building code...'
        sh 'mvn clean verify'
    }
    
    stage('Test Code') {
        echo 'Testing code...'
    }

    stage('Deploy Artifact') {
        echo 'Deploying artifact...'
        sh 'mvn deploy -Pacme-nexus'
    }

    stage('Docker Build') {
        echo 'Building Docker image...'
        sh 'docker-compose -f docker-compose.ci.yml build'
    }

    stage('Docker Push') {
        echo 'Pushing Docker image...'
        sh 'docker-compose -f docker-compose.ci.yml push'
    }
}