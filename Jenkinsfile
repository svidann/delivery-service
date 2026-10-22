
pipeline {
    agent any

    tools {
            maven 'Maven-3.10'
        }
        
    stages {
        stage('Checkout') {
            steps {
                echo 'Source code is checked out by Jenkins.'
            }
        }

        stage('Build') {
            steps {
                echo 'Compiling delivery service.'
                bat 'mvn -B clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn -B test'
            }
        }

        stage('Package') {
            steps {
                bat 'mvn -B package -DskipTests'
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution finished.'
        }
    }
}
