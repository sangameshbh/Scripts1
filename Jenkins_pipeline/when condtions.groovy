pipeline {
    agent any
    stages {
        stage ("test") {
            when {
                expression {environment == "test"}
            }
            steps {
                sh "echo build for test"
            }
        }
        stage ("QA") {
            when {
                expression {environment == "QA"}
            }
            steps {
                sh "echo build  for QA"
            }
        }
        stage ("Prod") {
            when {
                expression {environment == "Prod"}
            }
            steps {
                sh "echo build  for prod"
            }
        }
    }
}