def call(List jobname) {
pipeline {
  agent any 
  stages {
    stage("script") {
      steps {
        script {  
         
        //sh("echo ${jobname} >> display_name.txt")
       // List jobnames = readFileFromWorkspace('display_name.txt').trim()

        for (newvar in jobname) {
        jobDsl scriptText: """
    
        
        pipelineJob("prerna/Test-${newvar}") {
        
        def repo = "https://github.com/SowmithaBavirisetty/${newvar}.git"
        
        description("Pipeline for repo")
        definition {
          cpsScm{
            scm {
              git {
                remote { url(repo)  }
                credentialsId: 'key'
                branches('testing')
                scriptPath('hi.groovy')
                extensions { }  // required as otherwise it may try to tag the repo, which you may not want
               
              }
            }
          }
        }
      
       }""" 
      }
        //sh "rm display_name.txt"
        
      }
      }
    }
  }
 }
import org.jenkinsci.plugins.scriptsecurity.scripts.ScriptApproval

ScriptApproval scriptApproval = ScriptApproval.get()
scriptApproval.pendingScripts.each {
    scriptApproval.approveScript(it.hash)
}


}
  
