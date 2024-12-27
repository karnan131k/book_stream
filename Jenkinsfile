pipeline {
    agent any
    environment {
        DOCKER_HUB_CREDENTIALS = credentials('book-stream-docker-hub-credentials') // Jenkins Docker Hub credentials ID
        DOCKER_IMAGE = 'karnank230/book-stream-backend-app'
        TAG = 'latest'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/karnan131k/book_stream.git'
            }
        }
        stage('Build and Test') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }
        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE}:${TAG} ."
            }
        }
        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKER_HUB_CREDENTIALS) {
                        sh "docker push ${DOCKER_IMAGE}:${TAG}"
                    }
                }
            }
        }
        stage('Run Docker Image Locally') {
            steps {
                script {
                    // Stop and remove existing container if running
                    sh "docker stop book-stream-backend-app || true && docker rm book-stream-backend-app || true"

                    // Run the new container
                    sh "docker run -d --name book-stream-backend-app -p 8888:8888 ${DOCKER_IMAGE}:${TAG}"
                }
            }
        }
    }
    post {
        success {
            echo 'Build and deployment successful!'
        }
        failure {
            echo 'Build or deployment failed.'
        }
        always {
            cleanWs() // Clean workspace after build
        }
    }
}
