@ui @healthcheck
Feature: E-commerce Project Website Health Checkup

@LogoTitleValidation
Scenario: User is able to open the browser. nevigate to the URL and search for Product
Given user navigate to the home application url
When application logo is displayed
Then title of the landing page is "Automation Exercise"

@RegUserLogin
Scenario: User is able to visit application login page and with valid credentials able to login in app
Given user navigate to the home application url 
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