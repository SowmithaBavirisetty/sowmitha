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
        def var = "${var1}"
        echo "$var"
        jobDsl scriptText: "def var = \${var1}"
        jobDsl scriptText: """ 
        pipelineJob("var") {
        
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
  
