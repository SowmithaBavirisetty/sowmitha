// my-shared-library/vars/createPipelineJob.groovy
def call(Map config) {
    def jobName = config.jobName ?: 'defaultJobName'

    pipeline {
        agent any
        environment {
            JOB_NAME = "$jobName"
        }
        stages {
            stage('Build') {
                steps {
                    echo "Building ${env.JOB_NAME}"
                    // Additional build steps...
                }
            }
            stage('SCM Checkout') {
                steps {
                    script {
                        checkout scm
                    }
                }
            }
        }
    }
}


