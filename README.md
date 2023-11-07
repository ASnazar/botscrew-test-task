#  🚀University Console Application 🚀

This is a simple Spring Boot Java project that provides a console interface for managing a university's departments and lecturers. In this application, lecturers can work in multiple departments, and each lecturer can hold one of the following academic degrees: assistant, associate professor, or professor. The application stores all the data in a relational database.

## Tasks

The application implements the following commands:

1. **Who is head of department {department_name}**
    - User Input: Who is head of department {department_name}
    - Answer: Head of {department_name} department is {head_of_department_name}

2. **Show {department_name} statistics**
    - User Input: Show {department_name} statistics
    - Answer: assistans - {assistants_count}, associate professors - {associate_professors_count}, professors - {professors_count}

3. **Show the average salary for the department {department_name}**
    - User Input: Show the average salary for the department {department_name}
    - Answer: The average salary of {department_name} is {average_salary}

4. **Show count of employee for {department_name}**
    - User Input: Show count of employee for {department_name}
    - Answer: {employee_count}

5. **Global search by {template}**
    - User Input: Global search by {template}
    - Example: Global search by van
    - Answer: List of lecturer names matching the template

## Technologies

- Spring Boot
- Spring Data JPA
- H2 Database (for local development)
- Maven (as the build tool)
- Java 17
- Console-based user interface

## Running the Project

1. Clone or download the project from the GitHub repository.
2. Build and run the project using Maven.
3. Execute the management methods via the console.

## GitHub Repository

[GitHub Repository Link](https://github.com/ASnazar/botscrew-test-task.git)


## Author

Author: Nazar Balko
Contact: nazar.balko.job@gmail.com
