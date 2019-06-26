#!/usr/bin/groovy
def call() {
    // you can call any valid step functions from your code, just like you can from Pipeline scripts
    echo "Hello world, ${name}"
}