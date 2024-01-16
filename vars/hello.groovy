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




// my-shared-library/vars/CreateJobs.groovy
def call(List jobNames) {
    jobNames.each { jobName ->
        // Generate DSL code for each job
        def jobDslScript = """
            pipelineJob("prerna/Test-${jobName}") {
                description("Pipeline for ${jobName}")
                definition {
                    cpsScm {
                        scm {
                            git {
                                remote { url("https://github.com/SowmithaBavirisetty/${jobName}.git") }
                                
                                branches('testing')
                                scriptPath('hi.groovy')
                                extensions { }
                            }
                        }
                    }
                }
            }
        """

        // Create the job using the generated DSL code
        createJob(jobDslScript)
    }
}

// Function to create a job using DSL script
def createJob(String dslScript) {
    def jobManagement = new javaposse.jobdsl.plugin.ClasspathJobManagement()
    def jobDslEngine = new javaposse.jobdsl.dsl.DslScriptLoader(jobManagement).runScript(dslScript)
}
