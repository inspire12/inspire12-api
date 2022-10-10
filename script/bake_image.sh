./mvnw clean package -DskipTests=true
./mvnw package -DskipTests=true
sudo docker build --tag inspire12-api:0.0.1-SNAPSHOT .
