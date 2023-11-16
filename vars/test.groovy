def call(var1) {
pipeline {
  environment {
        
      def hi = "${var1.vse}"  
    }
  agent any 
  stages {
    stage("script") {
      steps {
        script {
        jobDsl scriptText: ''' 
        environmentVariables {
        keepSystemVariables(true)
        }
        def hlo = pipelineJob("${env.hi}")
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
  
