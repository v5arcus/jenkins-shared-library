def call(){
    docker.withRegistry('https://registry-intl.me-east-1.aliyuncs.com', 'dockerhub') {
        sh 'docker build -t ${REGISTRY_PATH}/${SERVICE_NAME}:${BUILD_NUMBER} .'
    }
}