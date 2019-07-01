def call() {
    withEnv(['JENKINS_MAVEN_AGENT_DISABLED=true']) {
        sh "/opt/maven/bin/mvn clean install"
    }
}
