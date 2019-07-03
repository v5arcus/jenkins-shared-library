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

        stage('current image service tag') {
            previousVersion = sh (
                    script: 'kubectl describe deployment ${SERVICE_NAME} -n ${ENVIRONMENT_NAME} --kubeconfig=${CLUSTER_CONFIG}| grep Image | awk -F \"/noonpay_development/${SERVICE_NAME}:\" \'{print $2}\' ',
                    returnStdout: true
                ).trim()            
        }


    }
}