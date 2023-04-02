Feature: Search for the product

### Please use endpoint GET https://waarkoop-server.herokuapp.com/api/v1/search/demo/{product} for getting the products.
### Available products: "orange", "apple", "pasta", "cola"
### Prepare Positive and negative scenarios

  Scenario: Verify that the orange endpoint displays only orange products
    Given he calls endpoint "https://waarkoop-server.herokuapp.com/api/v1/search/demo/orange"
    When he receives the results
    Then he sees the results displayed for orange

  Scenario: Verify that the apple endpoint displays only apple products
    Given he calls endpoint "https://waarkoop-server.herokuapp.com/api/v1/search/demo/apple"
    When he receives the results
    Then he sees the results displayed for apple

  Scenario: Verify that the car endpoint gives an error
    Given he calls endpoint "https://waarkoop-server.herokuapp.com/api/v1/search/demo/car"
    Then he does not see the results

  @Positive
  Scenario: Verify that the pasta endpoint displays the products in grams in the range 0-500
    Given he calls endpoint "https://waarkoop-server.herokuapp.com/api/v1/search/demo/pasta"
    When he receives the results
    Then he checks that all units are displayed in grams

  @Negative
  Scenario: Verify that a wrong url is throwing "Not Found"
    Given he calls endpoint "https://waarkoop-server.herokuapp.com/api/v1/search/dem/pasta"
    Then he sees a 404 Not Found error
