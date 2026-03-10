pipeline {
    agent any
    
    tools {
        maven 'Maven 3.8.6'
        jdk 'JDK 11'
    }
    
    parameters {
        choice(name: 'TAG', choices: ['@Smoke', '@Regression', '@DataDriven', '@Smoke or @Regression'], description: 'Select test tag to execute')
        choice(name: 'ENVIRONMENT', choices: ['QA', 'STAGING', 'PROD'], description: 'Select environment')
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code from repository...'
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                echo 'Building the project...'
                sh 'mvn clean compile'
            }
        }
        
        stage('Run Tests') {
            steps {
                echo "Running tests with tag: ${params.TAG}"
                echo "Environment: ${params.ENVIRONMENT}"
                sh "mvn clean test -Dcucumber.filter.tags='${params.TAG}'"
            }
        }
        
        stage('Generate Reports') {
            steps {
                echo 'Generating test reports...'
                cucumber buildStatus: 'UNSTABLE',
                    reportTitle: 'REST API Test Report',
                    fileIncludePattern: '**/*.json',
                    trendsLimit: 10,
                    classifications: [
                        [
                            'key': 'Environment',
                            'value': "${params.ENVIRONMENT}"
                        ],
                        [
                            'key': 'Tag',
                            'value': "${params.TAG}"
                        ]
                    ]
            }
        }
    }
    
    post {
        always {
            echo 'Archiving test results...'
            archiveArtifacts artifacts: 'target/**/*.json, target/**/*.html, target/logs/*.log', allowEmptyArchive: true
            
            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'target/cucumber-reports',
                reportFiles: 'overview-features.html',
                reportName: 'Cucumber HTML Report'
            ])
        }
        
        success {
            echo 'Tests passed successfully!'
        }
        
        failure {
            echo 'Tests failed. Please check the reports.'
        }
    }
}
