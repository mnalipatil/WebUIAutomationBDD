@ui @healthcheck
Feature: E-commerce Project Website Health Checkup

Background: Navigation to the base url of application
Given user navigate to the home application url

@LogoTitleValidation
Scenario: User is able to open the browser. nevigate to the URL and search for Product
#Given user navigate to the home application url
When application logo is displayed
Then title of the landing page is "Automation Exercise"

@RegUserLogin
Scenario: User is able to visit application login page and with valid credentials able to login in app
#Given user navigate to the home application url 
And user clicks on Signup/login button form top header section
When user redirected to login page with title as "Automation Exercise - Signup / Login"
And url for the login page contains "login" as keyword
And user able to see "Login to your account" section on login page
And user enters valid registered email id as "gebat52405@vikinoko.com"
And user enters valid password as "123@Abc"
And click on login button
Then after login user able to see "Logout" button at top header of application
And user is able to see "Delete Account" button at top header section of application
And with "Tom" as user name just after Logged in as button

@ProdSearch
Scenario Outline: User is able to search a product on the application
#Given user navigate to the home application url
When user header over to products page
And user redirected to products page with title as "Automation Exercise - All Products"
And url page login page containts the "products" as keyword
And user search for a product "<product_name>"
And click on search button
Then form the product list the first product contains the "<product_name>" as keyword
Examples:
	|product_name|
	| White      |
	| Top				 |
	| Dress      |
	| Tshirt     | 
	
@prodMainCategory
Scenario: Under brands different brand visibility validation
#Given user navigate to the home application url
When User is able to see "CATEGORY" header
Then Under category below list is displayed 
|WOMEN |
|MEN   |
|KIDS  |