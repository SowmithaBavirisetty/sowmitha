def call(variant) {  
    node {
     stage("build") {
        
        jobDsl scriptText: '''pipelineJob(\"variant1\") {
        def repo = \'https://github.com/SowmithaBavirisetty/sowmitha.git\'

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
