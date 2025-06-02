node {
    def mvnHome
    def app
    stage('Clone') { // for display purposes
         cleanWs()
    
        // Get some code from a GitHub repository
        git branch: 'main',
            url: 'https://github.com/sididboo/FR-MEDHEAD-POC-URGENCE.git'
            
        // Get the Maven tool.
        // ** NOTE: This 'M3' Maven tool must be configured
        // **       in the global configuration.
        mvnHome = tool 'M3'
    }
    stage('build-gateway') {
        // Run the maven build
        withEnv(["MVN_HOME=$mvnHome"]) {
            if (isUnix()) {
				sh '''cd gateway
                    $MVN_HOME/bin/mvn" -Dmaven.test.failure.ignore clean install\''''
            } else {
				bat '''cd gateway
				    "%MVN_HOME%\\bin\\mvn" -Dmaven.test.failure.ignore clean install'''
            }
        }
    }
    stage('build-hopital') {
        // Run the maven build
        withEnv(["MVN_HOME=$mvnHome"]) {
            if (isUnix()) {
				sh '''cd hospital
                    $MVN_HOME/bin/mvn" -Dmaven.test.failure.ignore clean install\''''
            } else {
				bat '''cd hospital
				    "%MVN_HOME%\\bin\\mvn" -Dmaven.test.failure.ignore clean install'''
            }
        }
    }
	stage('build-urgence') {
        // Run the maven build
        withEnv(["MVN_HOME=$mvnHome"]) {
            if (isUnix()) {
				sh '''cd urgence
                    $MVN_HOME/bin/mvn" -Dmaven.test.failure.ignore clean install\''''
            } else {
				bat '''cd urgence
				    "%MVN_HOME%\\bin\\mvn" -Dmaven.test.failure.ignore clean install'''
            }
        }
    }
    stage('test-hopital') {
        withEnv(["MVN_HOME=$mvnHome"]) {
            if (isUnix()) {
                sh '''cd hospital
                    $MVN_HOME/bin/mvn" surefire-report:report\''''
            } else {
                bat '''cd hospital
				    "%MVN_HOME%\\bin\\mvn" surefire-report:report'''
            }
        }
        junit 'hospital/target/surefire-reports/TEST-*.xml'
    }
    stage('test-urgence') {
        withEnv(["MVN_HOME=$mvnHome"]) {
            if (isUnix()) {
                sh '''cd urgence
                    $MVN_HOME/bin/mvn" surefire-report:report\''''
            } else {
                bat '''cd urgence
				    "%MVN_HOME%\\bin\\mvn" surefire-report:report'''
            }
        }
        junit 'urgence/target/surefire-reports/TEST-*.xml'
    }
    stage ('run') {
        withEnv(["MVN_HOME=$mvnHome"&&"JENKINS_NODE_COOKIE=dontKill"] ) {
            if (isUnix()) {
                sh '''
                    cd urgence/target
                    & java -jar urgence-0.0.1-SNAPSHOT.jar --httpPort=8082
                    & cd ../../hospital/target
                    & java -jar hospital-0.0.1-SNAPSHOT.jar --httpPort=8083
                    & cd ../../gateway/target
                    & java -jar gateway-0.0.1-SNAPSHOT.jar --httpPort=8081
                '''
            } else {
                bat '''
                    cd urgence/target && java -jar urgence-0.0.1-SNAPSHOT.jar --httpPort=8082 && cd ../../hospital/target && java -jar hospital-0.0.1-SNAPSHOT.jar --httpPort=8083 && cd ../../gateway/target && java -jar gateway-0.0.1-SNAPSHOT.jar --httpPort=8081
                '''
            }
        }
    }
    
}
