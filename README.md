# CRUD Project Documentation

## Overview

This project is a CRUD (Create, Read, Update, Delete) application built with Angular for the frontend, Spring Boot for the backend, and JWT for security. It utilizes REST APIs for communication between the frontend and backend.

You can take a look at following flow to have an overview of Requests and Responses that Angular 12 Client and spring boot will make or receive.
![angular-12-jwt-authentication-flow](https://github.com/Malak-Jazzazi/crud-project-frontend/assets/100603606/29320b26-7d72-4eb7-8e32-42caf804fe22)

## Setup

1. **Database Setup**:
    - Create a MySQL database at port 3306.
    - Username: root
    - Password: admin
    - Create a schema named `crud-data`.

2. **Backend Setup**:
    - Run the Spring Boot application.

3. **Frontend Setup**:
    - Navigate to the `crud-project` directory.
    - Run `ng serve` to start the frontend server.

## User Credentials

### Manager Role
- Username: malak
- Username: admin
- Password: 123123

### Employee Role
- Username: said
- Username: sara
- Username: firas
- Password: 123123

## Usage

1. **Login**:
    - Use one of the provided usernames and passwords to log in.

2. **Functionality**:
    - Once logged in, you can access CRUD functionalities according to your role.

## Technologies Used

| Component | Technology   |
|-----------|--------------|
| Backend   | Spring Boot  |
| Frontend  | Angular      |
| Security  | JWT          |

