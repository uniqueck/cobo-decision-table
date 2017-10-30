node {
	def mavenHome = tool 'M3'
	stage('Checkout') {
		checkout([$class: 'GitSCM', branches: [[name: '*/develop']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'Github', url: 'https://github.com/uniqueck/lfet.git']]])
	}
	stage('Build') {
		sh "${mavenHome}/bin/mvn clean compile"
	}
	stage('Test') {
		sh "${mavenHome}/bin/mvn test"
	}
	stage('Publish result') {
		archiveArtifacts artifacts: 'target/*.jar', fingerprint: true, onlyIfSuccessful: true
		junit '**/target/test-reports/*.xml'
	}

}