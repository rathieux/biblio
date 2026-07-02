pipeline {
    agent any

    stages {

        stage('Build & SonarQube') {
            agent {
                docker {
                    image 'maven:3.9-amazoncorretto-21'
                    args '-u root --network biblio'
                    reuseNode true
                }
            }

            steps {
                dir('biblio-back/bibliotheque') {
                    withSonarQubeEnv('sonar') {
                        sh '''
                            mvn clean verify \
                            org.sonarsource.scanner.maven:sonar-maven-plugin:sonar \
                            -Dsonar.projectKey=git-bibliotek \
                            -Dsonar.projectName="git-bibliotek"
                        '''
                    }
                }
            }
        }

        stage('Start Backend') {
            steps {
                dir('biblio-back/bibliotheque') {
                    sh 'docker compose up -d'
                }
            }
        }

        stage('Run Frontend') {
            steps {
                dir('biblio-front') {
                    sh 'docker rm -f biblio-angular || true'
                    sh 'docker build -t biblio-angular .'
                    sh 'docker run -d -p 4200:80 --name biblio-angular biblio-angular'
                }
            }
        }
    }
}