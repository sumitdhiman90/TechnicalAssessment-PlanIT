# TechnicalAssessment-PlanIT

Web UI automation scenarios for PlanIT Jupiter Toys application.

As per the requirement, 3 Test Case scenarios has been automated:

1. First scenario validates the mandatory fields on the contact page. This is the first test case in the ContactPageTests java class. 

2. Second scenario verifies feedback submit functionality 5 times on contact page. This is the second test case in the ContactPageTests java class.

3. Third scenario adds products to the cart, verifies the items added to the cart, verifies product price, subtotal and total on cart page. This test is in the CartPageTests java class.

**Technology stack**: 

Java, Selenium, WebDriverManager, Maven, TestNG, JenKins, Extent Reports.

**Setup**:

Required: Java 8, Maven, eclipse.

Go to New --> Project from version control --> enter Repository URL

The cloned project can also be imported into eclipse as a maven project. All dependencies present in pom.xml will get downloaded by Maven.

**TestNG file**

The test suite is defined in the testng.xml file. ContactPageTests and CartPageTests are two java classes containing all the test scenarios.

**Execution**:

All Tests can be executed from cmd prompt using the command (from project root directory)
"mvn test"

Contact Page and Cart Page test scenarios can be run separately from command line with following commands:
1. mvn clean test -Dtest="ContactPageTests"
2. mvn clean test -Dtest="CartPageTests"

Note: Maven should be installed and path should be set up in order to run commands from cmd/terminal

**Reporting**:

Reports are generated in "reports" folder. To view TestNG report, open index.html with browser. Test failure screenshots are saved in the "reports" folder and also displayed in report.
