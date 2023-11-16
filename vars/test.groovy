def call(var1) {
pipeline {
  agent any 
  stages {
    stage("script") {
      steps {
        script {
        def hi = "${var1.vse}"
        jobDsl scriptText: ''' 
        def hlo = pipelineJob("${hi}")
        hlo.with {
        def repo = 'https://github.com/SowmithaBavirisetty/sowmitha.git'

        description("Pipeline for $repo")
        definition {
          cpsScm{
            scm {
              git {
                remote { url(repo) }
                credentialsId: 'key'
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
}
  
