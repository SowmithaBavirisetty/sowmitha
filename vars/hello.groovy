def call(name) {
 pipelineJob('DSL_Pipeline') {

  //def repo = 'https://github.ibm.com/CryptoCAT/CLiC.git'

  description("Pipeline for $repo")

  definition {
    cpsScm{
      scm {
        git {
          remote { url($repo) }
          branches('', '**/feature*')
          scriptPath('hi.groovy')
          extensions { }  // required as otherwise it may try to tag the repo, which you may not want
        }

        // the single line below also works, but it
        // only covers the 'master' branch and may not give you
        // enough control.
        // git(repo, 'master', { node -> node / 'extensions' << '' } )
      }
    }
    sandbox()
  }
 }
}
