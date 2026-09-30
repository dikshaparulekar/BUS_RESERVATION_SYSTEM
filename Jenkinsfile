pipeline {
    agent any

    environment {
        MAVEN_SETTINGS = 'D:\\maven-settings.xml'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                bat "mvn -s ${env.MAVEN_SETTINGS} clean compile"
            }
        }
        stage('Test') {
            steps {
                bat "mvn -s ${env.MAVEN_SETTINGS} test"
            }
        }
        stage('Package') {
            steps {
                bat "mvn -s ${env.MAVEN_SETTINGS} package -DskipTests"
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: true
        }
    }
}
