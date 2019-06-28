def call() {
    withMaven() {
        sh """
    export JENKINS_MAVEN_AGENT_DISABLED=true
    mvn clean install
    """
    }
}