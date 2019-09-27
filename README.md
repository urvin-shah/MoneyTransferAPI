# Money Transfer API

Money transfer APIs project has mainly following 2 APIs :

  - Retrieve Balance
  - Create Deposit/Withdraw transaction

I have Prepared these APIs using the Springboot and H2 inmemory database.
# How to Run APIs!

  - Github URL : https://github.com/urvin-shah/MoneyTransferAPI use master branch only.
  - Download MoneyTransferAPI-0.0.1-SNAPSHOT.jar file from the Executables folder

You can execute jar using Command Prompt:
  - Open Command prompt
  - change directory using CD command till the Executables downloaded
  - apply command "java -jar MoneyTransferAPI-0.0.1-SNAPSHOT.jar" to execute it
  - Go to browser type "http://localhost:9090/swagger-ui.html" to open the Swagger UI
  - Click List Operations which will shows 2 endpoints as per the requirements
  - Give appropriate inputs and perform transactions
  - Sample value for Create Transaction endpoint :
  {
  "accountId": 4,
  "custId": 1,
  "transactionType": "WITHDRAW",
  "txnAmount": 200
 }
 - Sample Value for Retrieve Balance endpoint : 4
 - H2 Database : Url "http://localhost:9090/MoneyTransferDB", change the JDBC Url with value "jdbc:h2:mem:testdb"
 - Test the connection, and connect, it will show following 3 tables :
   1. ACCOUNT
   2. ACCOUNT_TRANSACTION
   3. CUSTOMER

I have used the Swagger third party liberary for Swagger integration you may find it's dependancy in the pom.xml file.

Swagger specification can be see using the url : "http://localhost:9090/swagger-ui.html"