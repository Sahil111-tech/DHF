Feature: Quick Quote Process
  As a user, I want to get a quick health insurance quote

  Background:
    Given I am on the quote page
    When I click the "Get Quote" button
    And I select cover for a single person
    And I click the "Next" button
    And I select the residence as "NSW"
    And I click the "Next" button

  Scenario: User completes the quick quote process
    And I enter my date of birth as "01/01/1980"
    And I select my annual income
    And I click the "Calculate Cover" button
    Then I should see the title "Get a private health insurance quote | Doctors' Health Fund"

  @starterExtras
  Scenario: User verifies Starter Extras Eligibility for Smart Starter Bronze Plus
    And I enter my date of birth as "01/01/1990"
    And I select my annual income
    And I click the "Calculate Cover" button
    Given the_user_selects_plan "Smart Starter Bronze Plus"
    When the user selects "Starter Extras"
    Then "Starter Extras" should be enabled

  @starterExtras
  Scenario: User verifies Starter Extras Eligibility for Prime Choice
    Given the_user_selects_plan "Prime Choice"
    When the user attempts to select "Starter Extras"
    Then "Starter Extras" should be disabled

  @starterExtras
  Scenario: User verifies Starter Extras Eligibility for Top Cover
    Given the_user_selects_plan "Top Cover"
    When the user attempts to select "Starter Extras"
    Then "Starter Extras" should be disabled

  @starterExtras
  Scenario: User verifies Starter Extras Eligibility when selecting Extras only
    And I enter my date of birth as "01/01/1990"
    And I select my annual income
    And I click the "Calculate Cover" button
    And I clicked on the cover type dropdown and selected Extras only.
    When the user attempts to select "Starter Extras"
    Then "Starter Extras" should be disabled
