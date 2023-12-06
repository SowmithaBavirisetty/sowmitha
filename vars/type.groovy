def call(var1) {
 
              
        sh("echo ${var1} >> display_name.txt")
        
        jobDsl scriptText: ''' 
        
        String jobname = readFileFromWorkspace('display_name.txt').trim()
        pipelineJob(jobname) {
        
        def repo = 'https://github.com/SowmithaBavirisetty/sowmitha.git'
        
        description("Pipeline for $repo")
        definition {
          cpsScm{
            scm {
              git {
                remote { url(repo) }
                credentialsId: 'key'
                branches(\'testing\')
                scriptPath(\'hi.groovy\')
                extensions { }  // required as otherwise it may try to tag the repo, which you may not want
              }
            }
          }
        }
       }'''
       sh "rm display_name.txt"
      
}
  
