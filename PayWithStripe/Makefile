# Makefile for building and running the Dockerized application

# Define variables for the Docker image and container names
IMAGE_NAME = paywithstripe
CONTAINER_NAME = paywithstripe

# Target to build the Spring Boot project using Maven
build-project:
	./mvnw clean install

# Target to build the Docker image
build:
	docker build -t $(IMAGE_NAME) .

# Target to run the Docker container
run:
	docker run -p 8000:8080 --name $(CONTAINER_NAME) $(IMAGE_NAME)

# Target to stop and remove the Docker container
stop:
	docker stop $(CONTAINER_NAME)
	docker rm $(CONTAINER_NAME)

# Target to build the project, build the Docker image, and run the container
build-and-run: build-project build run
