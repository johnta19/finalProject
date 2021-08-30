pipeline {
    agent any
    stages {
        stage('clean') {
            steps {
                'rm -rf allure-results'
                'rm -rf allure-report'
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

