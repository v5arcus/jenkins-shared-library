def call(cutting, pitting) {
    script {
        serviceName = sh (
            script: "echo ${POD_NAME} |  cut -d '-' -f 1",
            returnStdout: true
            ).trim()
        }    
        echo "We ${pitting} are going ${cutting} to deploy ${serviceName}"
}

// def call(String name) {
//     // you can call any valid step functions from your code, just like you can from Pipeline scripts
//     echo "Hello world, ${name}"
// }
