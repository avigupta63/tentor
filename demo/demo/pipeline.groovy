pipeline {
    agent any

    stages {
        stage('Pull') {
            steps {
                git branch: 'main', url: 'https://github.com/avigupta63/tentor.git'
            }
        }

        stage('Build') {
            steps {
                dir('demo/demo') {
                    sh '/opt/apache-maven/bin/mvn clean package'
                }
            }
        }

        stage('Test') {
            steps {
                echo 'Test success...'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploy success...'
            }
        }
    }
}
