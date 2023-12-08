def call(var1) {
pipeline {
  agent any 
  stages {
    stage("script") {
      steps {
        script {  
              
        sh("echo ${var1} > display_name.txt")
        
        jobDsl scriptText: """
        def jobname = params.jobname
        
        pipelineJob(jobname) {
        
        def repo = "https://github.com/SowmithaBavirisetty/${jobname}.git"
        
        description("Pipeline for repo")
        definition {
          cpsScm{
            scm {
              git {
                remote { url(repo)  }
                credentialsId: 'key'
                branches(\'testing\')
                scriptPath(\'hi.groovy\')
                extensions { }  // required as otherwise it may try to tag the repo, which you may not want
              }
            }
          }
        }
       }""" ,
       params: [
        jobname: readFileFromWorkspace('display_name.txt').trim()
       ]
       sh "rm display_name.txt"
      }
      }
    }
  }
 }
}
  
