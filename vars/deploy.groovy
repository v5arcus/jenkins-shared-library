def call() {
    sh "kubectl set image deployment/${SERVICE_NAME} ${SERVICE_NAME}=${REGISTRY_PATH}/${SERVICE_NAME}:${BUILD_NUMBER} -n ${ENVIRONMENT_NAME} --kubeconfig=${CLUSTER_CONFIG}"
    sh "kubectl rollout status deployment/${SERVICE_NAME} -n ${ENVIRONMENT_NAME} --kubeconfig=${CLUSTER_CONFIG}"
}