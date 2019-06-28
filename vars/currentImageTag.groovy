def call() {
    previousVersion = sh (
        script: 'kubectl describe deployment ${SERVICE_NAME} -n ${ENVIRONMENT_NAME} --kubeconfig=${CLUSTER_CONFIG}| grep Image | awk -F \"/noonpay_development/${SERVICE_NAME}:\" \'{print $2}\' ',
        returnStdout: true
    ).trim()
}