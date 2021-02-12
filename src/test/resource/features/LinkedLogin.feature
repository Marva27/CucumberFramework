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
@LinkedinLogin
Feature: Linkedin Login
  I want to test the functionality of Linkedin Login page

  @AttemptLogin
  Scenario Outline: Attempt Linkedin Login
    Given I am on Linkedin Login page
    When I enter my email address as "<userName>", password as "<password>"
    And I click the Login button
    Then I should see name as "<expProfileName>"

    Examples: 
      | userName               | password     | expProfileName    |
      | vijaymak2004@gmail.com | Sainiya@1215 | Vijay Govindasamy |


 