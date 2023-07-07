---
Problem Statement:
You are tasked with developing a Secure Banking System for a financial institution. The system should provide a secure platform for users to manage their accounts, perform transactions, and ensure data confidentiality and integrity.

The system must fulfill the following functional requirements:
1. Any user with an Admin role can view the list of all existing users.
2. Any user with an Admin role can add a new user and set his password and role in the system.
3. Any user with a Client role can view their outstanding balance.
4. Any user with a Client role can perform a credit transaction to their account for any value.
5. Any user with a Client role can perform a debit transaction for any amount less than or equal to their outstanding balance.
6. Any user with a Client role can view their transaction history.

Backend (Java, Spring Boot with Data JPA, MySQL):

The Backend should conform to the following Technical requirements:

 1. Implement following backend REST End points
   a. Authentication (All User types)- /authenticate
   [POST]
   {
    "username" : "John",
    "password" : "12345"
   }
   b. Fetch all Users (Admin) - /fetchusers
   [GET]
   c. Fetch transactions for a user (Clint) - /all-transactions?userId=john
   [GET]
   d. Create or Update User (Admin) - /user
   [POST]
   {
    "userId" : "test.user",
    "password" : "12345",
    "role" : "CLIENT"
   }
	
   e. Perform CREDIT or DEBIT transaction - /transaction
   [POST]
   { 
   "userId": "john",
    "transactionAmount" : 100,
    "transactionType" : "CREDIT"
    }

   f. Fetch Outstanding balance for a Client user (Client) - /out-standing?userId=John
   [GET]

2. Design and implement a secure database schema using MySQL to store user information, account details, and transaction history as per the defined entities. 
3. The database schema should include the following two-way relationships
        1. OneToMany from User -> UserTransaction & a reverse ManyToOne to User entities.
4. Implement authentication and authorization using Spring Security and JWT. 
5. Implement security measures such as input validation, output encoding, and protection against common security vulnerabilities.

You need to complete code in the following files:
1. UserTransactionController
2. UserController
3. JwtAuthenticationController
4. User.java
5. UserTransaction.java

Build the Frontend (Angular):

The frontend to the system should contain a view that allows users to login. The login page should accept the UserId, Password and Role. If the parameters pass all the authentication checks then the user should be able to login. Upon login the user should be able to perform all operations corresponding to their Role. 

Additionally, the user should be able to logout from the system whenever they want. This means that all views that are connected to the given backend endpoints will have a logout button.

The frontend should conform to the following requirements:
1. Create an Angular application to provide a user interface for managing users,performing transactions securely, adding new users and listing all users.
2. Any user that logs in with Client role should have a user interface to perform Credit and Debit transactions in their account. Additionally, they should be able to view outstanding balance and list of all transactions in this view.
3. Any user that logs in with Admin role should be able to view a list containing all existing users. This screen should contain a button for adding new users and redirect them to a UI for performing this operation.
4. Implement secure communication between the frontend and backend using HTTPS and ensure that sensitive data is encrypted in transit.
5. Implement client-side security measures such as input validation, output encoding, and protection against common web security vulnerabilities.
6. Display appropriate feedback messages to users to prevent potential security breaches (e.g., revealing sensitive information in error messages).

You need to complete code in the following files:
1. client/src/app/auth/services/auth.service.ts
2. client/src/app/bank/services/transaction.service.ts
3. client/src/app/auth/components/user/user.component.ts
4. client/src/app/bank/components/transaction/transaction.component.ts

Notes:
1. Database must support 2-way relationships
2. Implement service layer in Angular Project, connecting the web api core in the service layer with support of middle ware in SPRING BOOT.
3. You need to establish the relationship amongst entities present in the backend before you can successfully preview the application.