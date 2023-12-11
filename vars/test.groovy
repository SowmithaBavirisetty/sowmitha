def call(Map config) {
pipeline {
  agent any 
  stages {
    stage("script") {
      steps {
        script {  
              
        sh("echo ${config.jobname} > display_name.txt")
        
        jobDsl scriptText: """
        
        jobname = readFileFromWorkspace('display_name.txt').trim()
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
       }""" 
        sh "rm display_name.txt"
       }
      }
    }
  }
 }
}
  
