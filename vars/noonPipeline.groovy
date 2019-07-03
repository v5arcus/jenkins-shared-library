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
                sh "/opt/maven/bin/mvn clean install"
        }

        stage ('Build Service Docker Image') {
            docker.withRegistry('https://registry-intl.me-east-1.aliyuncs.com', 'dockerhub') {
                sh "docker build -t ${p.REGISTRY_PATH}/${p.SERVICE_NAME}:${p.BUILD_NUMBER} ."
                sh "docker push ${p.REGISTRY_PATH}/${p.SERVICE_NAME}:${p.BUILD_NUMBER}"
            }
        }

        stage ('deploy') {
            echo "We are going to deploy ${SERVICE_NAME}"
            sh "kubectl set image deployment/${SERVICE_NAME} ${SERVICE_NAME}=${REGISTRY_PATH}/${SERVICE_NAME}:${BUILD_NUMBER} -n ${ENVIRONMENT_NAME} --kubeconfig=${CLUSTER_CONFIG}"
            sh "kubectl rollout status deployment/${SERVICE_NAME} -n ${ENVIRONMENT_NAME} --kubeconfig=${CLUSTER_CONFIG}"
        }

    }
}