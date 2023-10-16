pipeline {
    agent any
    stages {
         stage ("folder") {
            steps {
                script {
                    try {
                        sh "mkdir test"
                    }catch(exec) {
                        echo "test already exist"
                    }
                }
            }
        }
    }
} 