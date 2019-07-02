def call() {
    node {

         def p = pipelineCfg()

        stage('listing'){
            sh 'pwd'
            sh 'ls'
            echo "${p.ENVIRONMENT}"
        }


    }
}