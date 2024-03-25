def call(List var1) {
  script {
    for (newvar in var1) {
      
      jobDsl scriptText: """
        pipelineJob("prerna/Build_${newvar}") {     
          def repo = "https://github.com/SowmithaBavirisetty/${newvar}.git"
          description("Pipeline for ${newvar}")
          definition {
            cpsScm { 
              scm {                                     
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
       def branchName = 'main'
       queue("prerna/Build_${newvar}") {
         parameters {
          param('BRANCH_NAME', branchName)
        }
       }
       
       """
      
     }        
   }
}
