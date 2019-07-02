def call() {
    node {

        def p = pipelineCfg()

        stage{
            echo "${p.environment}"
        }


    }
}