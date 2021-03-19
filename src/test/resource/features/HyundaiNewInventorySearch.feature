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
@NewInventory
Feature: Title of your feature
  I want to test the functionality of new inventory search

  #Background for login and common methods

  @AttemptNewInventorySearch
  Scenario Outline: New Inventory Search
    Given I navigate to hyundai home page
    When I click new inventory
    And I select filter model as "<Model>"
    And I select filter year as "<Year>"
    Then I verify the total count of the search result match with listed items in the search result

    Examples: 
      | Model    | Year |
      | Santa Fe | 2020 |
