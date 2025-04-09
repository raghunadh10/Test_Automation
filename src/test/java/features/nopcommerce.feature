@regression
Feature: nopCommerce Website Testing - Advanced Search

@test1
 Scenario Outline: Register a new user
    Given User is on registration page
    When User enters dynamic details and submits the form "<Gender>" "<First Name>" "<Last Name>" "<CompanyName>"
    Then User should be registered successfully

    Examples:
    |Gender|First Name|Last Name|CompanyName|
    |Male  |test|user001        |testCompany|

  @test2
  Scenario: Login with valid credentials
    Given User is on login page
    When User enters valid "testuser@example.com" and "Test@1234"
    And Clicks on Login button
    Then User should be logged in successfully

  @test3
  Scenario: Search for Apple Laptop and add to cart
    Given I open the nopCommerce website
    When I search for "Macbook"
    And I click on the first search result
    And I add the product to the cart
    Then I should see a success message
    And I fetch the product specifications and log them

    @test4 @test
  Scenario: Validate search message for a non-existent product
    Given I open the nopCommerce website
    When I search for a random product "xyzrandom123"
    And I click on the search button
    Then I should see the message "No products were found that matched your criteria."


  @test5
  Scenario: Verify error message when applying an invalid coupon
    Given I open the nopCommerce website
    And I navigate to the cart page
    When I enter a random coupon code "INVALID123"
    And I click on the apply coupon button
    Then I should see the coupon invalid message "The coupon code cannot be found"


@test6
  Scenario: Search for an existing product using advanced search filters
    Given User is on the nopCommerce search page
    When User enters "Laptop" in the search field
    And User enables Advanced Search
    And User selects "Computers" as the category
    And User clicks on the Search button
    Then User should see search results containing "Laptop"

  @test7
  Scenario: Search for an existing product using advanced search filters
    Given User is on the nopCommerce search page
    When User enters "Laptop" in the search field
    And User enables Advanced Search
    And User selects "Computers" as the category
    And User selects "Apple" as the manufacturer
    And User clicks on the Search button
    Then User should see search results containing "Laptop"


    @test8
  Scenario: Search for a non-existent product with a specific manufacturer
    Given User is on the nopCommerce search page
    When User enters "Gaming Chair" in the search field
    And User enables Advanced Search
    And User selects "Books" as the category
    And User selects "Nike" as the manufacturer
    And User clicks on the Search button
    Then User should see a message "No results were found."

  @test9
  Scenario: Search for a non-existent product in a specific category
    Given User is on the nopCommerce search page
    When User enters "gaming chair" in the search field
    And User enables Advanced Search
    And User selects "Books" as the category
    And User clicks on the Search button
    Then User should see a message "No results were found."




