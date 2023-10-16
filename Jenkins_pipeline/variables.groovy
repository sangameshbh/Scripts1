pipeline {
    agent any
    environment {
        tool = "docker"
    }
    stages {
        stage ("testing") {
            steps {
                sh "echo tool currently using is $tool"
                echo "hello world"
                print BUILD_NUMBER
                print WORKSPACE
                sh "echo $BUILD_NUMBER"
                sh "echo $WORKSPACE"
            }
        }
    }
}