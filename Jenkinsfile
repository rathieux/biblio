pipeline {
    agent any

    stages {
        stage('Maven Package') {
            agent {
                docker {
                    image 'maven:3.9-amazoncorretto-21'
                    args '''-v /Users/mathieu/.m2:/root/.m2:z
                            -u root
                            --network biblio
                        ''' 
                        
                        // Pour garder le cache Maven
                    reuseNode true // Permet de partager le même workspace que l'agent Jenkins
                }
            }

            steps {
                dir('biblio-back/bibliotheque') {
                        sh 'getent hosts host.docker.internal'
                        sh "mvn clean package"
                    }
                    
                }
            }
        }
}
