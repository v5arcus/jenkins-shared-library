#!/usr/bin/groovy
package io.noon;

def call(SERVICE_NAME) {
    serviceName = sh (
        script: "echo ${SERVICE_NAME} |  cut -d '-' -f 1",
        returnStdout: true
        ).trim()
    echo "${serviceName}"
}
