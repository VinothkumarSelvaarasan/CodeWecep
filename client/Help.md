---
Problem Statement:
You are tasked with developing a Secure Banking System for a financial institution. The system should provide a secure platform for users to manage their accounts, perform transactions, and ensure data confidentiality and integrity. 

Backend (Java, Spring Boot with Data JPA, MySQL):

The Backend should conform to the following requirements:
1. Implement a RESTful API to manage user accounts, transactions, and account balance updates.
2. Design and implement a secure database schema using MySQL to store user information, account details, and transaction history. 
3. The database schema should include the following two-way relationships:One-to-One=> An account belongs to one user, and a user has one account.
4. One-to-Many Relationship => A user can have multiple transactions, and a transaction belongs to one user.
5. Implement authentication and authorization using Spring Security and JWT. Only authenticated and authorized users should be able to access the banking functionalities.
6. Implement security measures such as input validation, output encoding, and protection against common security vulnerabilities (e.g., SQL injection, cross-site scripting).
7. Implement security controls to prevent common attacks, such as account enumeration, brute force attacks, and session hijacking.

You need to complete code in the following files:

Build the Frontend (Angular):
1. client/src/app/auth/services/auth.service.ts
2. client/src/app/bank/services/transaction.service.ts
3. client/src/app/auth/components/user/user.component.ts
4. client/src/app/bank/components/transaction/transaction.component.ts

The frontend should conform to the following requirements:
1. Create an Angular application to provide a user interface for managing bank accounts and performing transactions securely.
2. Implement secure communication between the frontend and backend using HTTPS and ensure that sensitive data is encrypted in transit.
3. Implement client-side security measures such as input validation, output encoding, and protection against common web security vulnerabilities.
4. Display appropriate feedback messages to users to prevent potential security breaches (e.g., revealing sensitive information in error messages).
Requirements:
Implement multi-factor authentication (MFA) to provide an extra layer of security for user login.
Implement transaction authorization checks to ensure that users have the necessary permissions to perform specific types of transactions.
Implement transaction logging and auditing mechanisms to track and monitor financial activities.
Implement rate limiting or other security controls to protect against brute force attacks.
Write unit tests for critical components and functionalities.
Provide clear documentation on how to set up and run the application.
You are expected to demonstrate your understanding and implementation of secure coding practices, two-way relationships in the MySQL database, authentication, authorization, input/output validation, encryption, and protection against common web security vulnerabilities.

You need to complete code in the following files:
1. UserTransactionController
2. UserController
3. JwtAuthenticationController

Notes:
Database must support 2-way relationships
Implementing service layer in Angular Project, connecting the web api core in the service layer with support of middle ware in SPRING BOOT.
The version should be as follows :Spring Boot – Ver 3.0 | MySQL – Ver 8.0 | Angular – Ver 14.0
Implementing the Reactive Form in Angular. This must inculde to show case of two-way binding, ng Module
Enable Service Layer in Angular
