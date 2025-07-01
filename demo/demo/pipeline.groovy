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
          sshagent (credentials: ['ec2-ssh-ohio']) {
                    sh '''
                        scp -o StrictHostKeyChecking=no demo/demo/target/demo-0.0.1-SNAPSHOT.jar ubuntu@18.191.129.243:/home/ubuntu/
                        ssh -o StrictHostKeyChecking=no ubuntu@18.191.129.243 'pkill -f "java -jar" || true && nohup java -jar /home/ubuntu/demo-0.0.1-SNAPSHOT.jar > app.log 2>&1 &'
                    '''
    }
}
                }
    }
}