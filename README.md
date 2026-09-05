Recipe Management System (Phase 1)
A Java-based Full Stack web application designed to store and manage culinary recipes. This project was developed as part of Formative Assessment #2 for the Java Full Stack course at Thakur College of Engineering and Technology (TCET).

🚀 Project Overview
This application follows the MVC (Model-View-Controller) architectural pattern to implement core CRUD (Create, Read, Update, Delete) operations. It allows users to add recipe names and ingredients, which are then persisted in a MySQL database and rendered dynamically on a web interface.

🛠️ Tech Stack
Backend: Java 17+, Jakarta EE (Servlets)

Frontend: JSP (JavaServer Pages), Bootstrap 5, CSS

Database: MySQL 8.0

Server: Apache Tomcat 10.1

Build Tool: Dynamic Web Project (Eclipse)

📂 Project Structure
Plaintext
src/main/java
 └── com.recipe.controller   # RecipeServlet (Handles routing and logic)
 └── com.recipe.model        # Recipe.java (POJO Data Model)
src/main/webapp
 ├── WEB-INF/lib             # MySQL Connector JAR
 └── recipe-list.jsp         # Frontend UI
⚙️ Setup & Installation
1. Database Setup
Run the following SQL script in your MySQL Workbench:

SQL
CREATE DATABASE recipe_db;
USE recipe_db;

CREATE TABLE recipes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    ingredients TEXT NOT NULL
);
2. Configure the Project
Clone the repository:

Bash
git clone https://github.com/Ayush2-7/Recipe-Management-System.git
Import the project into Eclipse IDE as a Existing Project.

Ensure the mysql-connector-j-x.x.x.jar is present in src/main/webapp/WEB-INF/lib.

Update the database password in RecipeServlet.java:

Java
private String dbPass = "your_password";
3. Running the App
Right-click on the project in Eclipse.

Select Run As > Run on Server.

Choose Apache Tomcat v10.1.

📸 Key Features (CRUD)
Create: Add recipe titles and ingredients via a Bootstrap-styled form.

Read: View all recipes fetched from the MySQL database in a dynamic table.

Delete: Remove recipes from the system using unique ID parameters.

🤝 Contribution
Developed by Ayush Yadav Computer Engineering Department, TCET Mumbai.
