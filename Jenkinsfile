
pipeline {
    agent any

    tools {
            jdk 'JDK-25'
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
                bat 'echo JAVA_HOME=%JAVA_HOME%'
                bat 'where java'
                bat 'where javac'
                bat 'java -version'
                bat 'javac -version'
                bat 'mvn -version'
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
//comment
    post {
        always {
            echo 'Pipeline execution finished.'
        }
    }
}
