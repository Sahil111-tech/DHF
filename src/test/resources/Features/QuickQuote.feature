Feature: Quick Quote Process
  As a user, I want to get a quick health insurance quote

  Scenario: User completes the quick quote process
    Given I open the browser
    And I am on the quote page
    When I click the "Get Quote" button
    And I select cover for a single person
    And I click the "Next" button
    And I select the residence as "NSW"
    And I click the "Next" button
    And I enter my date of birth as "01/01/1980"
    And I click the "Next" button
    And I select my annual income
    And I click the "Calculate Cover" button
    Then I should see the title "Get a private health insurance quote | Doctors' Health Fund"
   #And I close the browser
