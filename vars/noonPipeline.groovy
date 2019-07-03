def call() {
    node {

        stage('Checkout') {
            checkout scm
        }

         def p = pipelineCfg()


        stage('listing'){
            sh " export ${p.CLUSTER_CONFIG}"
            sh " cp /var/lib/jenkins/${p.CLUSTER_CONFIG} ${workspace}/"
        }

        stage('Updating serviceName') {
            serviceName = sh (
                    script: "echo ${SERVICE_NAME} |  cut -d '-' -f 1",
                    returnStdout: true
                ).trim()
        }


    }
}