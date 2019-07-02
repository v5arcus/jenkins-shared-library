def call() {
    node {
        stage{

            def p = pipelineCfg()

            stage('Update Cluster Config'){
                steps {
                    sh " export ${p.pipelineCfg}"
                    sh " cp /var/lib/jenkins/${p.pipelineCfg} ${workspace}/"
                }
            }
        }
    }
}