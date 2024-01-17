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
def call(List<String> jobNames) {
    jobNames.each { jobName ->
        // Generate DSL code for each job
        def jobDslScript = """
            pipelineJob('${jobName}') {
                description("Pipeline for ${jobName}")
                definition {
                    cpsScm {
                        scm {
                            git {
                                remote { url("https://github.com/SowmithaBavirisetty/${jobName}.git") }
                                
                                branches('master')
                                scriptPath('Jenkinsfile')
                                extensions { }
                            }
                        }
                    }
                }
            }
        """
        
        

        // No need to explicitly create the job, as the DSL plugin will apply it automatically.
    }
}
