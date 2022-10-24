./mvnw clean package -DskipTests=true
./mvnw package -DskipTests=true
sudo docker build --tag inspire12-api:0.0.3-SNAPSHOT .
sudo docker tag inspire12-api:0.0.3-SNAPSHOT inspire12-api:latest
