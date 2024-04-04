# Spring Boot Application with Gradle and Java 21

## Introduction

This project is a Spring Boot application developed using Gradle as the build automation tool and Java 21 for handling transactions and account creation. It provides a robust foundation for creating and retrieving accounts. It also provides support to perform debit/credit transaction in the created account.

## Features

- **Account Creation**: Allows users to create new accounts.
- **Account Retrieval**: Allows users to retrieve account.
- **Transaction Handling**: Provides functionalities for managing transactions associated with user accounts.

## Prerequisites

Before running the application, ensure that you have the following installed:

- Docker

## Getting Started

Follow these steps to set up and run the application:

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/siddharthrv/transaction.git

2. **Run the below command**:
    ```bash
    docker-compose up --build
    
Service will spin in port 8090

Mysql will be exposed in port 3070

Go to http://localhost:8090 to get API spec

Repo will have postman collection in root directory Transaction.postman_collection.json which can also be imported