def call(List var1) {
  script {
    for (newvar in var1) {
      // The "jobDsl" is the pipeline step in jenkins used to write the DSL script
      jobDsl scriptText: """
        pipelineJob("prerna/Build_${newvar}") {     // "pipelineJob" is used to create the the job of type Pipeline in jenkins
          def repo = "https://github.com/SowmithaBavirisetty/${newvar}.git"
          description("Pipeline for ${newvar}")
          definition {
            cpsScm {                                     // Loads a pipeline script from SCM.    
              scm {                                     // Specifies where to obtain a source code repository containing the pipeline script.
                git {
                  remote { 
                    url(repo)        
                  }
                  branches("*/\\\${BRANCH}")
                  scriptPath('Jenkinsfile')
                }
               }
             }
          }
       }
       queue("prerna/Build_${newvar}")
       """
      
     }        
   }
}
