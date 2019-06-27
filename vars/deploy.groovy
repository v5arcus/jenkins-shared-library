def call(deploy) {
    script {   
        echo "We are going to deploy ${POD_NAME}"
        sh "kubectl set image deployment/${POD_NAME} ${POD_NAME}=${REGISTRY_PATH}/${POD_NAME}:${BUILD_NUMBER} -n ${ENVIRONMENT_NAME} --kubeconfig=${CLUSTER_CONFIG}"
        sh "kubectl rollout status deployment/${POD_NAME} -n ${ENVIRONMENT_NAME} --kubeconfig=${CLUSTER_CONFIG}"
}