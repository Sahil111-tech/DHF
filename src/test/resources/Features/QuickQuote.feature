Feature: Quick Quote Process
  As a user, I want to get a quick health insurance quote
  Background:

  Scenario: User completes the quick quote process
    Given I am on the quote page
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
    
    @starterExtras
    Scenario: User verifies Starter Extras Eligibility
    Given I am on the quote page
    When I click the "Get Quote" button
    And I select cover for a single person
    And I click the "Next" button
    And I select the residence as "NSW"
    And I click the "Next" button
    And I enter my date of birth as "01/01/1990"
    And I click the "Next" button
    And I select my annual income
    And I click the "Calculate Cover" button
    Given the_user_selects_plan "Smart Starter Bronze Plus"
    When the user selects "Starter Extras"
    Then "Starter Extras" should be enabled
    
    Given the_user_selects_plan "Prime Choice"
    When the user attempts to select "Starter Extras"
    Then "Starter Extras" should be disabled

    Given the_user_selects_plan "Top Cover"
    When the user attempts to select "Starter Extras"
    Then "Starter Extras" should be disabled
    
    @starterExtras
    Scenario: User verifies Starter Extras Eligibility
    Given I am on the quote page
    When I click the "Get Quote" button
    And I select cover for a single person
    And I click the "Next" button
    And I select the residence as "NSW"
    And I click the "Next" button
    And I enter my date of birth as "01/01/1990"
    And I click the "Next" button
    And I select my annual income
    And I click the "Calculate Cover" button
    And I clicked on the cover type dropdown and selected Extras only.
    When the user attempts to select "Starter Extras"
    Then "Starter Extras" should be disabled
    
    
    
   
    
    