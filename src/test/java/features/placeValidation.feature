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
Feature: Validating Place API's

  @AddPlace @Regression
  Scenario Outline: Verify if place is being Successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "postPlaceAPI" with "post" http request
    Then the API call is success with status code 200
    And "status" in response body id "OK"
    And "scope" in response body id "APP"
    And verify place_id created  maps to "<name>" using "getPlaceAPI"

    Examples: 
      | name | language | address |
      | aaaa | English  | 123aaaa |
      | bbbb | French   | 123bbbb |
      | cccc | Punjabi  | 123cccc |

  @DeletePlace @Regression
  Scenario: Verify if delete place functionality is working fine
    Given Delete place payload
    When user calls "deletePlaceAPT" with "post" http request
    Then the API call is success with status code 200
    And "status" in response body id "OK"
