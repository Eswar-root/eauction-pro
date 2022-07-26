# E-Auction
Batch-1 training: E-Auction case study

### Technologies Used
1) Spring Boot as backend framework
2) Mongodb cloud as database
3) Swagger for API documentation
4) React as frontend framework

### Pre-requisites
* Java
* Maven
* NodeJS

### Project Setup
1) Clone the repo to local
2) Check if java version is as in pom.xml else update it.
3) Edit the username and password at application.properties for mongodb atlas
4) Open terminal and cd into the backend folder
5) mvn clean install
6) java -jar target/e-auction-1.0-SNAPSHOT.jar
7) API endpoints can be viewed at http://localhost:8080/swagger-ui/index.html#/
8) Open another terminal and cd into the frontend folder
9) npm install
10) npm start
11) UI for can be viewed at http://localhost:3000

### APIs Working
1) Seller
* POST Request - localhost:8080/e-auction/api/v1/seller/add-product
* GET Request - localhost:8080/e-auction/api/v1/seller/show-bids/{productId}
* GET Request - localhost:8080/e-auction/api/v1/seller/get-all-products
* DELETE Request - localhost:8080/e-auction/api/v1/seller/delete/{productId}

2) Buyer
* POST Request - localhost:8080/e-auction/api/v1/buyer/place-bid
* PUT Request - localhost:8080/e-auction/api/v1/buyer/update-bid/{productId}/{buyerEmailId}/{newBidAmount}