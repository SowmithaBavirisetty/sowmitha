def call(var1) {
pipeline {
  agent any 
  stages {
    stage("script") {
      steps {
        script {  
        writeFile file: 'display_name.txt', text: 'var = ${var1}'
        echo (var)
        //shell('echo var1 > display_name.txt')
        jobDsl scriptText: ''' 
        String jobname = readFileFromWorkspace('display_name.txt').trim()
        displayName(jobname)
        pipelineJob(jobname) {
        
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
  
