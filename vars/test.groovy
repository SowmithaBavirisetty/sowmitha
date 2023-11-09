
def call(repo) {
pipeline {
  agent any 
  stages {
    stage("script") {
      steps {
        jobDsl scriptText: '''pipelineJob(\'testPipeline\') {
        description("Pipeline for $repo")
        definition {
          cpsScm{
            scm {
              git {
                remote { url("${repo}") }
                branches(\'testing\', \'**/feature*\')
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
  
