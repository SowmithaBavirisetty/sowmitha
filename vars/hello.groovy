def fun(name) {
node {
    stage('build') {
        echo("hello ${name}")
    }
 }
}
