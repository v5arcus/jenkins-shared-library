def checkUser() {
    steps {
        sh " export ${CLUSTER_CONFIG}"
        sh " cp /var/lib/jenkins/${CLUSTER_CONFIG} ${workspace}/"
    }
}