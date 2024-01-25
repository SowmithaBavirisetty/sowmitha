/*def call(List jobNames) {
    script {
      jobNames.each { jobName ->
        pipelineJob("prerna/Test-${jobName}") {
            definition {
                cpsScm {
                    scm {
                        git {
                            remote { url("https://github.com/SowmithaBavirisetty/${jobName}.git") }
                            branches('testing')
                            scriptPath('hi.groovy')
                        }
                    }
                }
            }
        } 
    } 
  } 
}*/




// my-shared-library/vars/CreatePipelineJobs.groovy
def call(String jobname) {
    script {
        // Generate DSL code for each job
            pipelineJob('${jobname}') {
                description("Pipeline for jobname")
                definition {
                    cpsScm {
                        scm {
                            git {
                                remote { url("https://github.com/SowmithaBavirisetty/jobname.git") }
                                
                                branches('master')
                                scriptPath('Jenkinsfile')
                                extensions { }
                            }
                        }
                    }
                }
            }
        }
}
