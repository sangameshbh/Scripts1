pipeline {
    //agent { label 'Agent1' }
    agent { label "Agent2"}
    stages {
        stage ("git clone") {
            steps {
                git 'https://github.com/sangameshbh/helloworld.git'
            }
        }
        stage ("maven-build") {
            steps {
                sh "mvn clean package"
            }
        }
    }
}