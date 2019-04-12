# mcpizza


ersion: 0.2

env:
  variables:
    JAVA_HOME: "/usr/lib/jvm/java-8-openjdk-amd64"
    AWS_DEFAULT_REGION: "us-east-1"
    IMAGE_REPO_NAME: "cognibank"
    IMAGE_TAG: "latest"
  parameter-store:
    AWS_ACCOUNT_ID: "/cb/account/id"
    AWS_ACCESS_KEY_ID: "/cb/access/key/id"
    AWS_SECRET_ACCESS_KEY: "/cb/secret/access/key"

phases:
  install:
    commands:
      - echo Entered the install phase and installing gradle..
      - apt-get update -y
      - apt-get install -y gradle
  pre_build:
    commands:
      - echo Entered the pre_build phase and logging into Amazon ECR..
      - $(aws ecr get-login --no-include-email --region $AWS_DEFAULT_REGION)
  build:
    commands:
      - echo Entered the build phase, Building the Project and Creating a JAR in build/libs using ./gradlew build
      - echo Build started on `date`
      - ./gradlew build
  post_build:
    commands:
      - echo Entered the post_build phase...
      - echo Building the docker image..
      - docker build -f Dockerfile -t $IMAGE_REPO_NAME .
      - echo Pushing the docker image to the ECR..
      - docker tag $IMAGE_REPO_NAME:$IMAGE_TAG $AWS_ACCOUNT_ID.dkr.ecr.$AWS_DEFAULT_REGION.amazonaws.com/$IMAGE_REPO_NAME:$IMAGE_TAG
      - docker push $AWS_ACCOUNT_ID.dkr.ecr.$AWS_DEFAULT_REGION.amazonaws.com/$IMAGE_REPO_NAME:$IMAGE_TAG
