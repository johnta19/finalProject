pipeline {
    agent any
    stages {
        stage('clean') {
            steps {
                bat 'rm -rf allure-results'
                bat 'rm -rf allure-report'
            }
        }
        stage('test') {
            steps{
                node('master') {
                    bat 'mvn clean test'

                    script {
                        allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'target/allure-results']]
                        ])
                    }
                }
            }
        }
    }
}

