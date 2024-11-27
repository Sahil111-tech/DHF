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

  @CompleteMAFSubmission
  Scenario: Complete MAF Application Submission
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
    And I select the hospital cover as "Top Cover"
    And I select the extras cover as "Total Extras"
    And I click the Apply Now button for the selected plan
    And I select No for Doctors' Health Fund membership
    And I select Yes for Australian citizenship or permanent residency
    And I click on the Medical Practitioner button
    And I select "Resident" as medical practitioner
    And I select the "No" radio button for existing Avant member
    Then I submit the eligibility page.
    And I select the "Yes" radio button for existing Avant member
    And I select the "No" radio button for existing Avant member
    Then I submit the eligibility page.
    And I select the option "No" for transferring from another fund
    And click next button
    And I select "Mr" as the title
    And I enter a random full name
    And I select the gender as "Male"
    And click next button
    And the user enters and verifies a random email
    And the user enters a random phone number
    And the user enters and selects the value "123 A'" in the dropdown
    Then I submit the Your Details page
    And I select "Yes" for the question "Are all the people on this policy eligible for Medicare?"
    And I verify images against green and blue medicare options
    And I enter "2298233698" in the card number field
    And I enter "3" as the Card Reference Number
    And I enter "2" as the day, "12" as the month and "2028" as the year
    And I select "Yes" for claiming the Australian Government Rebate
    And I select "Yes" for policy coverage
    And I "select" the checkbox
    Then I submit the Medicare Details page
    And I select "No" for the question Do you wish to nominate a person other than yourself as an authorised person on the policy?
    And I select "No" for the Avant Group Member option
    And I select "Internet Search" from the How Did You Hear About Us dropdown
    And I select "No" for the check-in radio button after joining
    And I "accept" the Terms and Conditions checkbox
    And I "agree" the agree using personal information checkbox
    And I "accept" the AHSA Privacy Policy checkbox
    And I "confirm" the application truth checkbox
    Then I submit the additional information page
    And I "confirm" the application truth checkbox
    And I "confirm" the application truth checkbox
    Then I submit the additional information page
    And I enter the BSB number "032-059"
    And I enter a valid account number "85668757"
    And I enter a random account name
    And I select the direct debit agreement checkbox
    And I click on the submit form button
    And I toggle the bank account details checkbox by double-clicking
    Then I click on the submit form button
