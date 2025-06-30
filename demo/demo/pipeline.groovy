pipeline {
    agent any

    stages {
        stage('pull') {
            steps {
                git branch: 'main', url: 'https://github.com/avigupta63/tentor.git'
                // Add your pull commands here
            }
        }
        stage('build') {
            steps {
                sh 'mvn clean package'
                // Add your test commands here
            }
        }
        stage('test') {
            steps {
                echo 'test success...'
                // Add your deployment commands here
            }
        }
        stage('deploy') {
            steps {
                echo 'deploy success...'
                // Add your deployment commands here
            }
        }
    }


}