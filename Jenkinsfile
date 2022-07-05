pipeline {
    agent any

    environment {
        GIT_PATH = "https://github.com/DaviJam/GE-partiel.git"
        GIT_BRANCH = "selenium"
    }

    stages {

        stage('code source') {
	        when {
                not {
                    equals expected: true, actual: params.destroy
                }
            }
            steps {
                checkout([$class: 'GitSCM',
                    branches: [[name: "*/${GIT_BRANCH}"]],
                    doGenerateSubmoduleConfigurations: false,
                    extensions: [],
                    submoduleCfg: [],
                    userRemoteConfigs: [[
                        url: "${GIT_PATH}"
                    ]]
                ])
            }
        }

        stage('npm install') {
                    steps {
                        sh'npm install'
                    }
         }

        stage('npm run test') {
                             steps {
                                 sh'npm run test:allan'
                             }
        }



    }

}