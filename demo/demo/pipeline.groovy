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
                stage('Deploy') {
    steps {
        sh '''
        scp -i ohio.pem demo/demo/target/*.jar ubuntu@18.191.129.243:/home/ubuntu/
        ssh -i ohio.pem ubuntu@18.191.129.243 "pkill -f 'java -jar' || true && nohup java -jar /home/ubuntu/*.jar > app.log 2>&1 &"
        '''
    }
}

            }
        }
    }
}
