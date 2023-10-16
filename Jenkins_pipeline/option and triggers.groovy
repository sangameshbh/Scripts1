pipeline {
    agent any
    options {
        retry(3)
        buildDiscarder(logRotator(numToKeepStr: '5', daysToKeepStr: '2'))
        disableConcurrentBuilds()
        timeout(time:1, unit: 'MINUTES')
        timestamps()
    }
    triggers {
       // cron('* * * * *')
       pollSCM('* * * * *')
    }
    stages {
        stage("git clone") {
            steps {
                git 'https://github.com/sangameshbh/helloworld.git'
            }
        }
        stage("maven build") {
            steps {
                sh "mvn clean package"
            }
        }
    }
}