def call() {
    git branch: 'master',
    credentialsId: 'bitbucket-access',
    url: 'git@bitbucket.org:noon-wallet-engineering/${SERVICE_NAME}.git'
    sh 'ls -lrt'
}