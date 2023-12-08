def call(var1) {
pipeline {
  agent any 
  stages {
    stage("script") {
      steps {
        script {  
              
        sh("echo ${var1} >> display_name.txt")
        
        jobDsl scriptText: """
        
        String jobname = readFileFromWorkspace('display_name.txt').trim()
        pipelineJob(jobname) {
        
        
        
        description("Pipeline for repo")
        definition {
          cpsScm{
            scm {
              git {
                remote { url "https://github.com/SowmithaBavirisetty/${jobname}.git" }
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
  
