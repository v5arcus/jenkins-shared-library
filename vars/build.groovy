def call() {
    withMaven(){
        sh 'mvn --version'
        sh 'mvn clean install'
    }
}