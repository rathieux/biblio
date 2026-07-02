pipeline {
    agent any

    stages {
        stage('Start MySQL') {
            steps {
                dir('biblio-back/bibliotheque') {
                    sh 'docker compose up -d mysql'
                }
            }
        }

        stage('Maven Package') {
            agent {
                docker {
                    image 'maven:3.9-amazoncorretto-21'
                    args '''-v /Users/mathieu/.m2:/root/.m2:z
                            -u root
                            --network biblio
                        '''
                    reuseNode true
                }
            }

            steps {
                dir('biblio-back/bibliotheque') {
                    sh 'mvn clean package'
                }
            }
        }

    
    }
}