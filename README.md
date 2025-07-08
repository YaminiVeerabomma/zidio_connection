1. Use Spring Initializr
Go to 👉 https://start.spring.io

✅ Fill in Project Details:
Project: Maven / Gradle (Choose Maven for this guide)

Language: Java

Spring Boot Version: Choose latest stable version

Group: com.example

Artifact: your-project-name

Name: your-project-name

Packaging: Jar

Java: 17 or 21 (as supported in your IDE)

✅ Add Dependencies:
Choose based on your project needs, for example:

Spring Web

Spring Data JPA

MySQL Driver or H2 Database

Spring Security (if needed)

Lombok (optional but useful)

✅ Click: Generate
A .zip file will be downloaded

2. Extract the ZIP File
Extract the downloaded .zip file to a folder on your system.

This folder contains your full Maven-based Spring Boot project, including pom.xml.

3. Open Eclipse IDE
✅ Import the Project:
Go to: File > Import

Select: Maven > Existing Maven Projects

Click Next

✅ Select the Extracted Folder:
Browse and select the folder where you extracted your Spring Boot project

Eclipse will detect the pom.xml file

Click Finish

✔️ Eclipse will now import your Spring Boot project.

4. Wait for Maven to Download Dependencies
The first time you open the project, Eclipse/Maven will download all required JAR files

You can view this in the Console or Progress tab

5. Project Structure Ready!
Typical structure:

css
Copy
Edit
src/
 └── main/
     ├── java/
     │   └── com.example.yourproject/
     │        ├── controller/
     │        ├── service/
     │        ├── entity/
     │        ├── repository/
     │        └── YourApplication.java
     └── resources/
          ├── application.properties
          └── static/templates
✅ You’re Now Ready to Code!
Let me know if you want a template for:

Connecting to MySQL

Creating a REST API

Configuring JWT Security
...................................................................................................................................
| Command                   | Description                                            		
| ------------------------- | ------------------------------------------------------ 		
| `git init`                 Initialize a new Git repository                        		
| `git clone <repo_url>`  Clone an existing repository from GitHub or elsewhere 	
| `git status`              Show the status of files (tracked, untracked, staged) 		
| `git add <file>`          Stage changes of a specific file                      	
| `git add .`               Stage all changes in the current directory             	
| `git commit -m "message"` | Commit staged changes with a descriptive message       
| `git push`                | Push committed changes to the remote repository       		 
| `git pull`                | Fetch and merge changes from the remote repository    		 
| `git branch`              | List branches                                          
| `git checkout <branch>`   | Switch to another branch                               
| `git merge <branch>`      | Merge a branch into the current branch                 
| `git log`                 | View commit history                                    
| `git diff`                | Show changes between working directory and last commit 


