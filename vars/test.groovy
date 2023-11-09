def call(repo) {
pipeline {
  agent any 
  stages {
    stage("dsl-script") {
      steps {
        jobDsl scriptText: '''pipelineJob(\'dsl_Pipeline\') {
        description("Pipeline for $repo")
        definition {
          cpsScm{
            scm {
              git {
                remote { url("${repo}") }
                branches(\'dsl\', \'**/feature*\')
                scriptPath(\'hi.groovy\')
                extensions { }  // required as otherwise it may try to tag the repo, which you may not want
              }
            }
          }
        }
       }'''
      }
    }
  }
 }
}
  
