node {
	def mavenHome = tool 'M3'
	stage('Checkout') {
		scm {
			git branch : env.BRANCH_NAME url : scm.branch
		}	
	}

}