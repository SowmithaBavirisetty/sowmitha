
def type(List jobname) {

            
        jobname.each { jobname ->
        pipelineJob(jobname) {

        
        def repo = "https://github.com/SowmithaBavirisetty/${jobname}.git"
        
        description("Pipeline for $repo")
        definition {
          cpsScm{
            scm {
              git {
                remote { url(repo) 
                         credentialsId: 'key'
                       }
                
                branches('testing')
                scriptPath('hi.groovy')
                extensions { }  // required as otherwise it may try to tag the repo, which you may not want
              }
            }
          }
        }
       }
      }  
      
}
  
