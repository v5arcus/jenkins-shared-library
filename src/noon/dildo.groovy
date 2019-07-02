def call() {
    return([
        parameters([
        string(defaultValue: 'staging', name: 'CLUSTER_CONFIG', description: 'choose staging for t1/s1'),
        choice(choices: 'api-gateway\ncampaign-service\ncron-manager-service\ncustomer-support-dashboard\nnoonpay-web\ninstrumentation-service\nkyc-service\nload-money-service\nmerchant-gateway\nmerchant-identity-service\nmerchant-panel\nnotification-service\notp-service\np2p-service\nrecharge-service\nreporting-service\nspend-money-service\nuser-identity-service\nwithdraw-service\nbill-payments-service\n', description: 'Select the name of the service need to be deployed', name: 'SERVICE_NAME'),
        string(name: 'REGISTRY_PATH', defaultValue:"registry-intl.me-east-1.aliyuncs.com/noonpay_development", description: "Enter the container registry path"),
        choice(choices: 't0\ns0\nt1\ns1', name:'ENVIRONMENT_NAME', description: 'Environment where service needs to be deployed')
        ])
    ])
}
