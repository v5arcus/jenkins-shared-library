def call() {
    node {

        stage('Checkout') {
            checkout scm
        }

         def p = pipelineCfg()


        stage('listing'){
            sh 'pwd'
            sh 'ls'
            echo "${p.ENVIRONMENT}"
        }


    }
}