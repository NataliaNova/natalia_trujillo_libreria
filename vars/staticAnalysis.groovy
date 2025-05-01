def call(boolean abortPipeline = false) {
    withSonarQubeEnv('SonarQube') {
        echo 'Ejecución de las pruebas de calidad de código'
    }

       timeout(time: 5, unit: 'MINUTES') {
        def qg = waitForQualityGate()
        echo "Quality Gate status: ${qg.status}"

        def branch = env.BRANCH_NAME ?: 'unknown'
        echo "La rama actual es: ${branch}"

        if (abortPipeline) {
            error "Abortando pipeline porque abortPipeline = true"
        } else if (branch == 'master' || branch.startsWith('hotfix')) {
            error "Abortando pipeline porque la rama es master o empieza por hotfix"
        } else {
            echo "La rama no requiere abortar. Continuando..."
        }
    }
}
