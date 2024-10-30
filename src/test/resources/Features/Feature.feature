Feature: Quick Quote Process
  As a user, I want to get a quick health insurance quote, So that I can evaluate my coverage options effectively.

#Code if browser want to launch only once
  @starterExtras
  Scenario: User verifies Starter Extras Eligibility
  
   Given I am on the quote page
    When I click the "Get Quote" button
    And I select cover for "Single"
    And I click the "Next" button
    And I select the residence as "NSW"
    And I click the "Next" button
    And I enter my date of birth as "01/01/1987"
    And I click the "Next" button
    And I select the annual income as "Below $97k"
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

    
    #2nd scenerio starting from here :
    Given I clicked on the coverage type dropdown and selected "Extras only".
    When the user attempts to select "Starter Extras"
    Then "Starter Extras" should be disabled
    
    #3rd scenario starting from here :
    Given I clicked on edit button
    And I clicked on the coverage for dropdown and selected "Couple"
    And I enter my partner date of birth as "01/01/1989"
    And I clicked on update details button
    And the user clicks on "Choose Covers"
    Given the_user_selects_plan "Smart Starter Bronze Plus"
    When the user selects "Starter Extras"
    Then "Starter Extras" should be enabled
    Given I clicked on the coverage type dropdown and selected "Extras only".
    When the user attempts to select "Starter Extras"
    Then "Starter Extras" should be disabled
    And I clicked on edit button
    And I clicked on the coverage for dropdown and selected "Family"
    And I clicked on update details button
    
    Given I clicked on the coverage type dropdown and selected "Hospital & Extras".
    When the user attempts to select "Starter Extras"
    Then "Starter Extras" should be disabled
    
    Given I clicked on the coverage type dropdown and selected "Extras only".
    When the user attempts to select "Starter Extras"
    Then "Starter Extras" should be disabled
    
    #4th scenario starting from here :
    Given I clicked on edit button
    And I clicked on the coverage for dropdown and selected "Single Parent"
    And I clicked on update details button
    
    Given I clicked on the coverage type dropdown and selected "Hospital & Extras".
    When the user attempts to select "Starter Extras"
    Then "Starter Extras" should be disabled
    
    Given I clicked on the coverage type dropdown and selected "Extras only".
    When the user attempts to select "Starter Extras"
    Then "Starter Extras" should be disabled

