pipeline {
    agent any
    triggers { // Sondear repositorio a intervalos regulares
        pollSCM('* * * * *')
    }
    tools {
        maven "maven-plugin"
    }

    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "JAVA_HOME = ${JAVA_HOME}"
                    echo "JENKINS_VERSION = ${JENKINS_VERSION}"
                ''' 
            }
        }
        stage("Compile") {
            steps {
                sh "mvn compile"
            }
        }
        stage("Unit test") {
            steps {
                sh "mvn test"
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                    jacoco sourceInclusionPattern: '**/*.java'
                }
            }
        }
        stage("SonarQube Analysis") {
            steps {
                withSonarQubeEnv('SonarQubeDockerServer') {
                    sh 'mvn clean verify sonar:sonar'
                }
            }
        }
        stage("Build") {
            steps {
                sh "mvn package -DskipTests"
                archiveArtifacts 'target/*.jar'
            }
        }
        stage("Deploy") {
            steps {
                sh "mvn install -DskipTests"
            }
        }
    }
    post {
        always {
            mail to: 'team@example.com',
                subject: "Resultado Paso a produccion: ${currentBuild.fullDisplayName}",
                body: "${env.BUILD_URL} has result ${currentBuild.currentResult}"
        }
    }
}
