jobDsl scriptText: '''pipelineJob(\'DSL_Pipeline\') {

  def repo = \'https://github.com/SowmithaBavirisetty/sowmitha.git\'

  description("Pipeline for $repo")

  definition {
    cpsScm{
      scm {
        git {
          remote { url(repo) }
          branches(\'dsl\', \'**/feature*\')
          scriptPath(\'hi.groovy\')
          extensions { }  // required as otherwise it may try to tag the repo, which you may not want
        }

        // the single line below also works, but it
        // only covers the \'master\' branch and may not give you
        // enough control.
        // git(repo, \'master\', { node -> node / \'extensions\' << \'\' } )
      }
    }
  }
 }'''
