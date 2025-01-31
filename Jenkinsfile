pipeline {
    agent any
    tools {
        jdk 'openjdk17' // Use the correct name from Global Tool Configuration
        maven 'maven'   // Use the correct name from Global Tool Configuration
    }
    environment {
        DOCKER_HUB_CREDENTIALS = 'book-stream-docker-hub-credentials' // Jenkins Docker Hub credentials ID
        TAG = 'latest'
        VERSION = getVersion(GIT_BRANCH)
        DOCKER_IMAGE_NAME = getTagName(VERSION, BUILD_NUMBER)                                                   // Docker image name
        DOCKER_CONTAINER_NAME = 'book-stream-backend-app'                       // Docker container name
        PORT = '8888'                                                           // Exposed port
        HOST_PORT = '8888'
    }
    stages {
        stage('Build and Test') {
            steps {
                echo 'Building the project and running tests...'
                bat '.\\mvnw clean package -DskipTests'
            }
        }
        stage('Build Docker Image') {
            steps {
                echo 'Building Docker image...'
                bat "docker build -t ${DOCKER_IMAGE_NAME}:${TAG} ."
            }
        }
        stage('Push Docker Image to Docker Hub') {
            steps {
                echo 'Pushing Docker image to Docker Hub...'
                withCredentials([usernamePassword(credentialsId: DOCKER_HUB_CREDENTIALS, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    bat """
                    docker login -u %DOCKER_USER% -p %DOCKER_PASS%
                    docker tag ${DOCKER_IMAGE_NAME} %DOCKER_USER%/${DOCKER_IMAGE_NAME}:latest
                    docker push %DOCKER_USER%/${DOCKER_IMAGE_NAME}:latest
                    """
                }
            }
        }
        stage('Deploy') {
            steps {
                // Check if container exists, then stop and remove it
                echo 'Deploying the Docker container...'
                bat """
                docker ps -a --filter "name=${DOCKER_CONTAINER_NAME}" -q | findstr . && docker stop ${DOCKER_CONTAINER_NAME} || echo 'No container to stop'
                docker ps -a --filter "name=${DOCKER_CONTAINER_NAME}" -q | findstr . && docker rm ${DOCKER_CONTAINER_NAME} || echo 'No container to remove'
                docker run -d --name ${DOCKER_CONTAINER_NAME} -p ${HOST_PORT}:${PORT} ${DOCKER_IMAGE_NAME}
                """
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
    }
}

def getVersion(String gitBranch) {
    def parts = gitBranch.tokenize('/')
    return parts[-1]  // Return the last part of the branch name
}

def getTagName(String version, String buildId) {
    def tagname = version + '-' + buildId
    return tagname  // Return the tag name
}