pipeline {
    agent any
    tools {
        jdk 'openjdk17' // Use the correct name from Global Tool Configuration
        maven 'maven'   // Use the correct name from Global Tool Configuration
    }
    environment {
        DOCKER_HUB_CREDENTIALS = credentials('book-stream-docker-hub-credentials') // Jenkins Docker Hub credentials ID
        DOCKER_IMAGE = 'karnank230/book-stream-backend-app'
        TAG = 'latest'
    }
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out the source code...'
                git branch: 'main', url: 'https://github.com/karnan131k/book_stream.git'
            }
        }
        stage('Build and Test') {
            steps {
                echo 'Building the project and running tests...'
                bat '.\\mvnw clean package -DskipTests'
            }
        }
        stage('Build Docker Image') {
            steps {
                echo 'Building Docker image...'
                bat "docker build -t ${DOCKER_IMAGE}:${TAG} ."
            }
        }
        stage('Push Docker Image to Docker Hub') {
            steps {
                echo 'Pushing Docker image to Docker Hub...'
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKER_HUB_CREDENTIALS) {
                        bat "docker push ${DOCKER_IMAGE}:${TAG}"
                    }
                }
            }
        }
        stage('Run Docker Image Locally') {
            steps {
                echo 'Running Docker container locally...'
                script {
                    // Stop and remove the existing container if it's running
                    bat """
                    docker ps -q --filter "name=book-stream-backend-app" | findstr . && docker stop book-stream-backend-app || echo "No running container to stop"
                    docker ps -a -q --filter "name=book-stream-backend-app" | findstr . && docker rm book-stream-backend-app || echo "No container to remove"
                    """

                    // Run the new container
                    bat "docker run -d --name book-stream-backend-app -p 8888:8888 ${DOCKER_IMAGE}:${TAG}"
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
            stage('Cleanup') {
                steps {
                    echo 'Cleaning up the workspace...'
                    cleanWs()
                }
            }
        }
    }
}