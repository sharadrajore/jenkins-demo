pipeline{
    agent any
    
    stages{
        stage("Checkout"){
            steps{
                git changelog: false, poll: false, url: 'https://github.com/sharadrajore/jenkins-demo.git'
            }
        }
        stage("Build"){
            steps{
                bat "mvn -DskipTests clean install" 
            }
        }
        stage("Test"){
            steps{
                bat "mvn test" 
            }
        }
         stage("Deploy"){
            steps{
                deploy adapters: [tomcat8(credentialsId: 'tomcat-cred', path: '', url: 'http://localhost:9090/')], 
                contextPath: null, onFailure: false, war: '**/*.war'
            }
        }
    }
}
