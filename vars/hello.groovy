def call(List jobNames) {
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
}
