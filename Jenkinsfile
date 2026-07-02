pipeline {
    agent any

    stages {

        stage('Maven Package') {
            agent {
                docker {
                    image 'maven:3.9-amazoncorretto-21'
                    args '''
                        -u root
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

        stage('Start Backend') {
            steps {
                dir('biblio-back/bibliotheque') {
                    sh 'docker rm -f biblio-angular || true'
                    sh 'docker compose up -d'
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