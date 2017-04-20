#!groovy
pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Branch: ${BRANCH_NAME}'
            }
        }
        stage('After') {
            steps {
                echo 'After'
            }
        }
    }
}