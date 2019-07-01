def call() {
    serviceName = sh (
        script: "echo ${SERVICE_NAME} |  cut -d '-' -f 1",
        returnStdout: true
        ).trim()
    echo "${serviceName}"
}
