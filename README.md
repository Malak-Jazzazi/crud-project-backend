# Central Supply Unit Software Simulation

This project simulates a simple model of a Central Supply Unit software. The system includes the following modules and functionalities:

---

## 1. Main Login Page

Two types of users can log in:

- **Manager**
- **Employee**

### User Properties:
- User ID  
- User Full Name  
- Username  
- Password  
- User Type  

Only users with correct credentials (Username and Password) can access the system. Invalid logins must trigger a **client-side validation alert**.

> **Note:** A `Users` table should be created in the database with dummy data for both user types.

---

## 2. Warehouse Module (Manager Only)

This module is accessible only by users of type **Manager**.

### a. Warehouse View Page
Displays all warehouses created by the logged-in manager along with all items inside each warehouse.

#### Warehouse Properties:
- Warehouse Name  
- Warehouse Description  
- Created By  
- Created Date and Time  

#### Requirements:
- Use a grid component to display warehouses.
- Each row should include a **View** button to display the warehouse's items.
- Use a **stored procedure** to fetch warehouse data.

#### Buttons (Outside the Grid):
- **Add Warehouse:** Navigates to the Add Warehouse page.
- **Delete Warehouse:** Deletes the selected warehouse (use stored procedure).
- **Export:** Exports list of warehouses and their items to an Excel file.

---

### b. Add Warehouse Page

Allows the manager to add a new warehouse and its items.

#### Warehouse Fields:
- **Warehouse Name** (Mandatory, Unique – client-side validation)
- **Warehouse Description** (Optional)
- **Created By** (Auto-set as logged-in user's ID)
- **Created Date and Time** (Auto-set as current datetime)

#### Item Fields (Each Warehouse Can Have Multiple Items):
- Item Name  
- Item Description  
- Quantity  

> **Note:** Use a stored procedure to handle the add process.

---

## 3. Supply Document Module (All Users)

This module is accessible by both **Employees** and **Managers**, with different views and permissions.

---

### Employee View

#### a. Supply Documents View Page
Displays all supply documents created by the logged-in employee.

#### Supply Document Properties:
- Supply Document Name  
- Supply Document Subject  
- Created By  
- Created Date and Time  

#### Buttons (Outside the Grid):
- **Add Supply Document:** Navigates to Add Supply Document page.
- **Delete Supply Document:** Deletes the selected supply document (use stored procedure).

#### Note:
- Use a **stored procedure** to fetch supply documents.

---

#### b. Add Supply Document Page

Allows the employee to add a new supply document.

- The page must include a dropdown to select a **warehouse**.
- A second dropdown should populate **items** based on the selected warehouse.

> Each supply document must reference **one item** from **one warehouse**.

---

### Manager View

#### a. Supply Documents View Page

Displays all supply documents created by employees.

- Each row should show the employee who created the document.
- Include **Approve** and **Decline** buttons.

> **Note:** Use a **stored procedure** to fetch supply documents.


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
