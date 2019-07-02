#!/usr/bin/groovy
package io.noon;

def call(CLUSTER_CONFIG) {
    sh " export ${CLUSTER_CONFIG}"
    sh " cp /var/lib/jenkins/${CLUSTER_CONFIG} ${workspace}/"
}