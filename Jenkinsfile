pipeline {
    agent any

    stages {

        stage('Start compose') {
            steps {
                dir('biblio-back/bibliotheque') {
                    sh 'docker rm - f biblio-angular || true'
                    sh 'docker compose up -d '
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
                    sh 'mvn clean package -DskipTests'
                }
            }
        }


        stage('Run Frontend') {

            steps {

                dir('biblio-front') {

                    sh 'docker build -t biblio-angular .'

                    sh 'docker run -d -p 4200:80 --name biblio-angular biblio-angular'

                }

            }

        }

    
    }
}