# Student Info Portal System

## Developed by Srimadhav Mula and Bhargav Nallamalla

This Information Portal System designed as the course project of Object-Oriented-Programming mimics the functionality of a University Student website.


### Key Features:
                1. The data for this Info Portal is stored in MongoDB Atlas Database
                2. Whenever the program runs, the data in the Databae is read and is converted to student objects.
                3. Creating and Updating the data is requested by Students, which is checked by the Admin.
                4. Admin can View, Update(even without Students' requests), Create new Students.
                5. Creating and Updating are all the methods in the classes Admin and Student, and the medical queries in the class Hostel_Affairs.
                6. Concepts of Object-Oriented-Programming used: Classes, Objects, Method Overriding, Access Modifiers, Encapsulation

### Tools Used:
                1. Java Swing, a built-in GUI library in java, is used for the User Interface design, and the Interface made the project Self-Explanatory
                2. MongoDB Atlas, a cloud-based database, was used in the project, hence the project can be accessed remotely i.e different systems can access the same data (through proper login).
                3. For the dependency management, i.e as working with MongoDB is not supported directly by the libraries in the Java-Development-Kit, the dependency management tool Maven is used.

### Working:
            The application opens into a UI window that has two tabs, one has all the information about the campus i.e Map, Culturals, Sports, Curriculum
            The second tab has three buttons, one for a new student to enroll himself/herself, one for admin to login, on for existing student to login,
            The new student enroll asks for the student details and sends to the Admin for Admin's consent
            The existing Student when logs in, checks his/her data,and might request for an update if any required, which will be processed by the Admin, and can book a hospital visit
            Admin when logs in can see all the students, can add/update the students data and process the update/enroll requests.