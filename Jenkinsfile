pipeline {
  agent any
  stages {

    stage('Git') {
       steps {
        echo 'Pulling...' 
        git branch: 'maryeem-Branch',
        url : 'https://github.com/abbesachraf/tp-foyer.git'
      }
    }
      
    stage('MVN Clean') {
       steps {
        sh "mvn clean"      
      }
    }

      stage('MVN Compile') {
       steps {
        sh "mvn compile"      
      }
    }
    
    stage('MVN Package') {
      steps {
        sh "mvn package"      
      }
    }
      
    stage('Dependency Check') {
      steps {
        script {
          dependencyCheck additionalArguments: '--scan . --format HTML --out target/dependency-check-report', 
                          odcInstallation: 'DP-Check'
        }
      }
    }
       stage('Sonarqube Analysis') {
         steps {
         withSonarQubeEnv('Sonarqube1') {
         sh "mvn sonar:sonar \
         -Dsonar.projectKey=tpfoyer \
         -Dsonar.host.url=http://192.168.137.131:9000"
}
}
}
        stage('Nexus') {
         steps {   nexusArtifactUploader artifacts: [
           [artifactId: 'tp-foyer', 
            classifier: '', 
            file: 'target/tp-foyer-5.0.0.jar', 
            type: 'jar']], 
           credentialsId: 'Nexus', 
           groupId: 'org.springframework.boot', 
           nexusUrl: '192.168.137.131:8081', 
           nexusVersion: 'nexus3', 
           protocol: 'http', 
           repository: 'maven-releases', 
           version: '3.3.4'    
         }
        }

            stage('Docker Image'){
            steps{
                script{
                    sh 'docker build -t maryeeem/tp-foyer .'
                }
            }
        }
 
  
         stage('Docker Hub'){
            steps{
                script{
                   withCredentials([string(credentialsId: 'dockerhubpwd', variable: 'dockerhubpwd')]) {
                   sh 'docker login -u maryeeem -p ${dockerhubpwd}'

}
                   sh 'docker push maryeeem/tp-foyer'
                }
            }
        }

     // Exécution des tests JUnit
        stage('JUnit Test') {
            steps {
                echo 'Running JUnit tests...'
                sh 'mvn test'  // Exécuter les tests JUnit
            }
        }

        // Archiver les résultats des tests JUnit
        stage('JUnit Results') {
            steps {
                echo 'Archiving JUnit test results...'
                junit '**/target/test-*.xml'  // Collecter les résultats JUnit
            }
        }

        // Ajouter des pièces jointes aux résultats des tests
        stage('JUnit Attachments') {
            steps {
                script {
                    // Joindre des fichiers de logs ou autres pièces jointes générées durant le build
                    archiveArtifacts artifacts: '**/target/*.log', allowEmptyArchive: true
                    // Vous pouvez ajouter d'autres fichiers à joindre ici si nécessaire
                }
            }
        }
}
}
