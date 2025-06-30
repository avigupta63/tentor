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
                sh '''mvn sonar:sonar   -Dsonar.projectKey=tentor   -Dsonar.host.url=http://18.223.43.200:9000   -Dsonar.login=3dbfe233c14100b3a63c765c3c6114c1b00ce63f
'''
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploy success...'
            }
        }
    }
}
