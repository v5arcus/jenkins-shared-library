def call() {
    withEnv(['JENKINS_MAVEN_AGENT_DISABLED=true']) {
        sh "mvn package"
    }
}
