def call(String jobname) {
pipeline {
  agent any 
  stages {
    stage("script") {
      steps {
        script {  
              
        sh("echo ${jobname} >> display_name.txt")
        
        jobDsl scriptText: """
        
        String jobname = readFileFromWorkspace('display_name.txt').trim()
        
        pipelineJob(""prerna"/"Test-"+jobname") {
        
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
  
