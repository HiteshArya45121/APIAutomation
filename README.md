# API AUTOMATION FRAMEWORK ASSIGNMENT
------------------------------------------------------------
**Framework Structure**

1. src/main/java - 
	Contains   Framework level constants, 
			    Enums
			    Runtime Payload Creation						POJO classes
				Test Data get and set utilities
				TestNG Listeners
				Extent and log4j reporting

2. src/test/java
				
	Contains 	Test classes
				SpecBuilder and RequestMethods (GET, POST and PUT)

3. TestData.xlsx
				 
	Contains Payload for a particular request

**How to run the Project from Local machine**
1. Pull the code into your machine and import in IDE (Eclipse/intelliJ).
2. Update the Maven Project.
3. Go to testng.xml -> Run this file as TestNG suite
  It should start the execution .
 - **NOTE:** Global.properties is the default configuration file.
4. To view the Extent reports 
 - Go to your project location
 - Go to folder extent-report and open ExtentReportsTestNG.html


**Others implementations:**
1. Retry failed test cases
2. Custom Enums, Exceptions
3. Serialization and Deserialization 
4. Automatically open the report after tests execution.
5. Request and Response details added in ExtentReports.
6. Supports Parallel Execution.
7. log4j console reporting

**FRAMEWORK Goals:**
1. Scalable and extensible
2. Reusable Rest Assured Specifications.
3. Sepration of API layer with Test Layer.
4. User POJO for Serialization and Deserialization.
5. Lobbok for reducing Boilerplate code.
6. Robust Reporting and logging using Extent Report.
7. DataDriven.


**Future Goals:**

 Project can be integrated with Jenkins and We can provide the user specified Testng.xml suite,
 Scheduling Automated Run, Environment variables and Automatic Emailing the Reports.


**Tools and Tech stack used:**
1. Rest Assured
2. TestNG
3. Java
4. Extent Report.
5. lombok





