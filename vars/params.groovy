def call() {
    parameters {
        string(defaultValue: 'staging', name: 'CLUSTER_CONFIG', description: 'choose staging for t1/s1')
        choice(choices: 't0\ns0\nt1\ns1', name:'ENVIRONMENT_NAME', description: 'Environment where Pod running')
        string(defaultValue: "registry-intl.me-east-1.aliyuncs.com/noonpay_development", description: 'Registry Path', name: 'REGISTRY_PATH')
        choice(choices: 'api-gateway\ncampaign-service\ncron-manager-service\ninstrumentation-service\nload-money-service\nmarketing-panel\nmerchant-gateway\nmerchant-identity-service\nnotification-service\notp-service\np2p-service\nrecharge-service\nreporting-service\nspend-money-service\nuser-identity-service\nwithdraw-service\nbill-payments-service\ncustomer-support-dashboard\nkyc-service\nmerchant-panel\nmerchant-ops-panel', description: 'Select the name of the service need to be deployed', name: 'POD_NAME')
        string(defaultValue: "latest", description: 'Build Number want to deploy', name: 'BUILD_NUMBER')
    }
}