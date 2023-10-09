def call(name) {
node {
    stage('build') {
        echo("hello ${name}")
    }
 }
}
