def call(String name) {
    // you can call any valid step functions from your code, just like you can from Pipeline scripts
    echo "Hello world, ${name}"
}

def call(cutiing) {
    script {
        serviceName = sh (
            script: "echo ${POD_NAME} |  cut -d '-' -f 1",
            returnStdout: true
            ).trim()
        }    
}