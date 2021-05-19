# Banking App
## Project Description
A banking application using a web-based user interface with a backend written in Java.

### Technologies Used
* HTML
* JavaScript
* Bootstrap
* Java
* Javalin
* JDBC
* JUnit
* Mockito
* Log4J
* Gradle
* PostgreSQL

### Features
List of features ready and TODOs for future development

* Customers can:
  * log in
  * make deposits
  * make withdrawals
  * transfer money to other accounts
* Employes can:
  * log in
  * view customer transaction logs

To-do list:

* Add "Apply for Account" to the user interface
* Add ability for employees to approve or deny accounts
* Improve user interface and add more styling

### Getting Started

git clone https://github.com/MHarris-hub/Banking-App

1. modify the file: "Banking-App/src/main/java/com/usfbank/app/dao/util/PostgreSQLConnection.java" with your personal database settings
2. run the script contained in database setup script.txt to build the database and some dummy data
3. start the server by running the "Banking-App/src/main/java/com/usfbank/app/main/MainServer.java" file
4. navigate to "Banking-App/html/home.html" to begin
