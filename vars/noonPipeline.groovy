def call() {
    node {

        // def p = pipelineCfg()

        stage{
            sh 'pwd'
            sh 'ls'
            // echo "${p.ENVIRONMENT}"
        }


    }
}