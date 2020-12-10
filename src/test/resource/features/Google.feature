#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@LinkedInLogin
Feature: Login to LinkedIn
  I want to test the login functionality of LinkedIn application

  @ValidLoginCredentials
  Scenario: Attempt LinkedIn Login with valid credentials
    Given I am on LinkedIn login page
    When I enter valid username as "srinimarva@gmail.com"
    And I enter valid password as "Orange@27"
    And I click Login button
    Then I should see the LinkedIn Home page