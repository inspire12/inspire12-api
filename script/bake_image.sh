./mvnw clean
cp ../secret/application-oauth.yml ./src/main/resources/application-oauth.yml
cp ../secret/application-real-db.yml ./src/main/resources/application-real-db.yml
./mvnw package -DskipTests=true
sudo docker build --no-cache --tag inspire12/inspire12-api:0.0.3-SNAPSHOT .
sudo docker tag inspire12/inspire12-api:0.0.3-SNAPSHOT inspire12/inspire12-api:latest
