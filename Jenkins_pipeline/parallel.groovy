pipeline {
    agent any
    stages {
        stage ("parallel-jobs") {
            parallel {
                stage ("git-clone") {
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
    }
}