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
                    script: "echo ${p.SERVICE_NAME} |  cut -d '-' -f 1",
                    returnStdout: true
                ).trim()
        }

        // stage('current image service tag') {
        //     previousVersion = sh (
        //             script: "kubectl describe deployment p.SERVICE_NAME -n p.ENVIRONMENT_NAME --kubeconfig=p.CLUSTER_CONFIG| grep Image | awk -F \"/noonpay_development/p.SERVICE_NAME:\" \'{print $2}\' ",
        //             returnStdout: true
        //         ).trim()            
        // }

        stage('Let\'s Build') {
                sh 'mvn --version'
                sh 'mvn clean install'
        }

    }
}