pipeline {
    agent any

    environment {
        GIT_PATH = "https://github.com/DaviJam/GE-partiel.git"
        GIT_BRANCH = "selenium"
    }

    stages {

        stage('récupération du code source et récupération de la bonne branch') {
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

        stage('récupération du code source et récupération de la bonne branch') {
                    steps {
                        sh'npm install'
                    }
         }

        stage('récupération du code source et récupération de la bonne branch') {
                             steps {
                                 sh'npm run test:allan'
                             }
        }



    }

}