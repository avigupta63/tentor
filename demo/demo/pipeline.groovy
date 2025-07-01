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
               echo "test success"
            }
        }

        stage('Deploy') {
            steps {
                deploy adapters: [tomcat9(alternativeDeploymentContext: '', credentialsId: 'ed305a25-4a1b-4a2b-b400-d5ea2916b134', path: '', url: 'http://3.145.61.1:8080/')], contextPath: '/', war: '**/*.war'
            }
        }
    }
}
