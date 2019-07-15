def call() {
    node {

        stage('Checkout') {
            checkout scm
        }

         def p = pipelineCfg()


        stage('Prerequistes'){
            sh " export ${p.CLUSTER_CONFIG}"
            sh " cp /var/lib/jenkins/${p.CLUSTER_CONFIG} ${workspace}/"
            serviceName = sh (
                    script: "echo ${p.SERVICE_NAME} |  cut -d '-' -f 1",
                    returnStdout: true
                ).trim()
        }

        stage('Build & Test') {
                sh "/opt/maven/bin/mvn --version"
                sh "/opt/maven/bin/mvn clean install"
        }

        stage ('Push Docker Image') {
            docker.withRegistry('https://registry-intl.me-east-1.aliyuncs.com', 'dockerhub') {
                sh "docker build -t ${p.REGISTRY_PATH}/${p.SERVICE_NAME}:${BUILD_NUMBER} ."
                sh "docker push ${p.REGISTRY_PATH}/${p.SERVICE_NAME}:${BUILD_NUMBER}"
            }
        }

        stage ('Deploy') {
            echo "We are going to deploy ${p.SERVICE_NAME}"
            sh "kubectl set image statefulset/${p.SERVICE_NAME} ${p.SERVICE_NAME}=${p.REGISTRY_PATH}/${p.SERVICE_NAME}:${BUILD_NUMBER} -n ${p.ENVIRONMENT_NAME} --kubeconfig=${p.CLUSTER_CONFIG}"
            sh "kubectl rollout status statefulset/${p.SERVICE_NAME} -n ${p.ENVIRONMENT_NAME} --kubeconfig=${p.CLUSTER_CONFIG}"

            if ("${p.SERVICE_NAME}" == 'merchant-gateway') {
                sh "ssh kubecli yaml_cli -f /home/kubecli/k8s-manifest/${p.ENVIRONMENT_NAME}/helm-${p.ENVIRONMENT_NAME}/application/values.yaml -n gateway:image_tag ${BUILD_NUMBER}"
            }
            else if ("${p.SERVICE_NAME}" == 'merchant-identity-service') {
                sh "ssh kubecli yaml_cli -f /home/kubecli/k8s-manifest/${p.ENVIRONMENT_NAME}/helm-${p.ENVIRONMENT_NAME}/application/values.yaml -n identity:image_tag ${BUILD_NUMBER}"
            }
            else if ("${p.SERVICE_NAME}" == 'merchant-panel') {
                sh "ssh kubecli yaml_cli -f /home/kubecli/k8s-manifest/${p.ENVIRONMENT_NAME}/helm-${p.ENVIRONMENT_NAME}/application/values.yaml -n panel:image_tag ${BUILD_NUMBER}"
            }
            else if ("${p.SERVICE_NAME}" == 'merchant-ops-panel') {
                sh "ssh kubecli yaml_cli -f /home/kubecli/k8s-manifest/${p.ENVIRONMENT_NAME}/helm-${p.ENVIRONMENT_NAME}/application/values.yaml -n merchant:image_tag ${BUILD_NUMBER}"
            }
            else {
                sh "ssh kubecli yaml_cli -f /home/kubecli/k8s-manifest/${p.ENVIRONMENT_NAME}/helm-${p.ENVIRONMENT_NAME}/application/values.yaml -n ${serviceName}:image_tag ${BUILD_NUMBER}"
            }
        }

        stage ('Is our Pod running ?') {
            sleep (5)
            status = sh (
                script: "kubectl get pods -n ${p.ENVIRONMENT_NAME} -o=jsonpath='{range .items[*]}{@.status.containerStatuses[0].ready}:{@.metadata.name}' --kubeconfig=${p.CLUSTER_CONFIG}| grep ${p.SERVICE_NAME} |  cut -d ':' -f 1", 
                returnStdout: true
            ).trim()
            if (status == "true") {
                mail_status = "Success"
            }
            else {
                mail_status = "Failure"
            }
                    
        }

    }
}