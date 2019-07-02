def call() {
    sleep (5)
    status = sh (
        script: "kubectl get pods -n ${ENVIRONMENT_NAME} -o=jsonpath='{range .items[*]}{@.status.containerStatuses[0].ready}:{@.metadata.name}' --kubeconfig=${CLUSTER_CONFIG}| grep ${SERVICE_NAME} |  cut -d ':' -f 1", 
        returnStdout: true
    ).trim()
    if (status == "true") {
        mail_status = "Success"
    }
    else {
        mail_status = "Failure"
    }
    
    echo "${mail_status}"
}