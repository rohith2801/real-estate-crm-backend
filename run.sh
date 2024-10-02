mvn clean package -DskipTests
IMAGE_NAME="real-estate-crm-backend"
echo "Building $IMAGE_NAME ......"
docker build -t "$IMAGE_NAME" .
docker run -p 9228:9228 "$IMAGE_NAME"