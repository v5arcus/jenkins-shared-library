def call() {
    sh 'mvn --version'
    sh 'mvn clean install'
}