node{
    def WORKSPACE = "/var/lib/jenkins/workspace/springboot-deploy"
    def dockerImageTag = "springboot-deploy${env.BUILD.NUMBER}"

    try{
        stage('Clone Repo'){
            git ur: 'https://gitlab.com/gingeriot/gingeriot_backend.git',
                credentialsId: 'gitlab_cred',
                branch: 'main'
        }

        stage('Build docker'){

        }
    }catch(e){
       throw e
    }
}