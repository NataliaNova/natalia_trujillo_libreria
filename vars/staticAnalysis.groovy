def call(Boolean abortPipeline = false) {
    withSonarQubeEnv('SonarQube') {
        echo 'Ejecución de las pruebas de calidad de código'
    }
    timeout(time: 5, unit: 'MINUTES') {
        waitForQualityGate abortPipeline: abortPipeline
    }
}
