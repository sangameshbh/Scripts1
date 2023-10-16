pipeline {
    agent any
    environment {
        PATH="/usr/share/maven/bin:$PATH"
    }
    stages {
        stage ("git-checkout") {
            steps {
                cleanWs()
                git credentialsId: 'Jenkins-cred', url: 'https://github.com/sangameshbh/jenkins-kubernetes.git'
            }
        }
        stage ("create-war") {
            steps{
                sh "mvn clean install"
            }
        }
        stage ('Server'){
            steps {
               rtServer (
                 id: "jfrog",
                 url: 'http://54.236.43.166:8082/artifactory',
                 username: 'admin',
                  password: 'Sang@9538',
                  bypassProxy: true,
                   timeout: 300
                        )
            }
        }
        stage('Upload'){
            steps{
                rtUpload (
                 serverId:"jfrog" ,
                  spec: '''{
                   "files": [
                      {
                      "pattern": "*.war",
                      "target": "my-first-project"
                      }
                            ]
                           }''',
                        )
            }
        }
        stage ('Publish build info') {
            steps {
                rtPublishBuildInfo (
                    serverId: "jfrog"
                )
            }
        }
        stage ("deploy_tomcat") {
            steps{
                sshagent(['new-tomcat']) {
                    sh "scp -o StrictHostKeyChecking=no /var/lib/jenkins/workspace/project/webapp/target/webapp.war ec2-user@34.207.174.37:/opt/tomcat/webapps"
                }
            }
        }
        stage ("docker-image") {
            steps {
                sshagent(['docker-id']) {
                    sh "docker build -t sang9538/my-testing ."
                }
            }
        }
        stage ("push image") {
            steps {
                withCredentials([string(credentialsId: 'dockerhub', variable: 'dockpswd')]) {
                   sh "docker login -u sang9538 -p ${dockpswd}"
                   sh "docker push sang9538/nodeapp"
                }
            }
        }
        stage ("deploy on k8s") {
            steps {
                sshagent(['k8s']) {
                    sh '''
                    scp -o StrictHostKeyChecking=no nodejsapp.yaml ubuntu@44.204.177.111:/home/ubuntu/
                    kubectl apply -f nodejsapp.yaml 
                        '''        
                }
                        
            }
        }
    }
}