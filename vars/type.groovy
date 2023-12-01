def call(var1) {
pipeline {
  agent any 
  stages {
    stage("script") {
      steps {
        script {  
        //def newFile = new File("${WORKSPACE}/display_name.txt")
        //newFile.createNewFile()
        //writeFile file: 'display_name.txt', text: var1        
        //shell('echo var1 > display_name.txt')
        //def var = new "${var1}"
         parameters {
          string(name:"${var1}", defaultValue:'defaultval', description:'A parameter')
        }
        
        jobDsl scriptText: """  
        
        pipelineJob(var1) {
        
        def repo = "https://github.com/SowmithaBavirisetty/sowmitha.git"
        
        description("Pipeline for repo")
        definition {
          cpsScm{
            scm {
              git {
                remote { url("\$repo") }
                credentialsId: 'key'
                branches(\'testing\', \'**/feature*\')
                scriptPath(\'hi.groovy\')
                extensions { }  // required as otherwise it may try to tag the repo, which you may not want
              }
            }
          }
        }
       }"""
       
      }
    }
  }
 }
 
} 
  
}  
