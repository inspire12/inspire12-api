cp ../secret/application-oauth.yml ./src/main/resources/application-oauth.yml
cp ../secret/application-real-db.yml ./src/main/resources/application-real-db.yml

./mvnw clean package -DskipTests=true
./mvnw package -DskipTests=true
sudo docker build --tag inspire12/inspire12-api:0.0.3-SNAPSHOT .
sudo docker tag inspire12/inspire12-api:0.0.3-SNAPSHOT inspire12/inspire12-api:latest
