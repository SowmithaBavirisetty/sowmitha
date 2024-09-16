def call(variant,link) {
  pipeline {
    agent any
    stages {
        stage('Hello') {
            steps {
                echo("${variant}:${link}")
            }
        }
    }
  }
}
