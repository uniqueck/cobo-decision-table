node {
	def mavenHome = tool 'M3'
	stage('Checkout') {
		checkout([$class: 'GitSCM', branches: [[name: '*/develop']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'Github', url: 'https://github.com/uniqueck/lfet.git']]])
	}

}