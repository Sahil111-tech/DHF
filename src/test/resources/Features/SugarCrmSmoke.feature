Feature: Sugar CRM Process
  As a user, I want to login into sugar crm and create the leads.

  #Code if browser want to launch only once
  @sugarcrmverification
  Scenario: User logs in sugar crm and create the leads
    Given I launch the application URL
    And I click on the Show log in form link
    When I enter the username "amasso"
    And I enter the password "Pass1234!"
    And I click on the login button
    Then I should see the page title as "Agent Dashboard » Home » UAT"
    And I click on the Leads button
    And I click on the Create button
    And I select "Mrs" from the Salutation dropdown
    And the user enters a random full name into the input fields
    #When the user enters "Chamil" as the first name, "Michael" as the middle name, and "Dayajeewa" as the last name
    And the user selects "Doctors" from the Brand dropdown
    And the user selects "Inbound Call" from the Lead Source dropdown
    And the user click on the Lead Type dropdown and traverses 1st step
    And the user selects "1. Medical Practitioner" from the Eligibility ID dropdown
    And I select the PRCitizen checkbox
    And the user enters "15/06/1995" as the birth date
    And I generate and enter a random phone number
    And the user enters a random email
    And User types "123" in the suggestion input box and selects the 4 option using keyboard
    And the user selects "Cold" from the Agent Lead Score dropdown
    And the user click on the Eligibility details and select 1 'CMO' from the dropdown
    And the user click on the Gender dropdown and traverses 1 step
    And the user click on the how did you hear about us dropdown and selects 1 'DHF Sales Specialist' from the dropdown
    And the user navigates to the Previous Health Fund tab
    And the user click on the Previous Health Fund dropdown and select 2 'ACA Health Benifits Fund' from the dropdownn
    And the user click on the Previous Fund Transfer dropdown and traverses 2 step
    And I check the Cooling off checkbox
    And the user click on the 31st birthday dropdown and traverses 1 'Yes' step
    And I enter a random Health Fund Membership ID
    And I enter a policy name
    And I click on the Save button
    #And I search the user by entering the First&Last name
    And I click on the first user fullname in the table
    And I click on the Medicare button
    And I click on the Edit button
    And the user click on the medical card type dropdown and traverses 1 'Green' step
    And I enter the Medicare card number "2330743489"
    And the user click on the medical expiry month dropdown and traverses 5th step
    And the user click on the applicant claiming rebate dropdown and traverses 2nd step
    And the user click on the all people hold policy dropdown and traverses 1st step
    And I enter the Medicare card expiry year "2025"
    And I click on the Save button
    And I click on compliance tab
    And I click on the Edit button
    And I select the Hospital-extra Waiting Periods checkbox
    And I select the Exclusions-Restrictions checkbox
    And I select the Rebate checkbox
    And I select the LHC checkbox
    And I select the Excess Rules-Waiting Periods checkbox
    And I click on the Save button
    And I click on the Affiliation tab
    And I click on the Association
    And the user click on the association dropdown and traverses 1st step
    #And the user selects "Not applicable" from the association dropdown
    And I click on the Save button
    And I click on the Product
    And the user click on the class dropdown and traverses 1st step
    And the user click on the hospital dropdown and traverses 1st step
    And the user click on the extra dropdown and traverses 1st step
    And I click on the Financial
    And the user click on the payment frequency dropdown and traverses 1st step
    And I click on the calculate quote button
    And I click on the save_1 button
    And I capture the success message and close the popup
    And I click on the Financial Info
    And I click on the Edit button
    And The user clicks on the Group dropdown, enters the "direc" into the suggestion input box, and selects the 1 option using the keyboard.
    And the user click on the debit account type dropdown and traverses 1 step
    And The user enters a random name in the Debit Bank Account Name field
    And I enter the valid BSB number "032-059"
    And The user enters "354647585" in the Debit Bank Account Number field
    And The user enters a random name in the Benefit Bank Account Name field
    And I enter the valid benefit BSB number "062-062"
    And The user enters "404647585" in the Benefit Bank Account Number field
    And I click on the Save button
    And I click on the Wrap Up Tab
    And I click on the Edit button
    And The user enters joining date
    And the user click on the welcome call dropdown and traverses 1st step
    And The user selects the Claiming Process checkbox
    And The user selects the OMS Registration checkbox
    And the user click on the authority level dropdown and traverses 2nd step
    And I click on the Save button
    And The user clicks on the Convert to Membership button
    Then I capture the membership creation message and validate the ID

  @sugarcrmverificationwithcreditcard
  Scenario: User logs in sugar crm and create the leads
    Given I launch the application URL
    And I click on the Show log in form link
    When I enter the username "amasso"
    And I enter the password "Pass1234!"
    And I click on the login button
    Then I should see the page title as "Agent Dashboard » Home » UAT"
    And I click on the Leads button
    And I click on the Create button
    And I select "Mrs" from the Salutation dropdown
    And the user enters a random full name into the input fields
    #When the user enters "Chamil" as the first name, "Michael" as the middle name, and "Dayajeewa" as the last name
    And the user selects "Doctors" from the Brand dropdown
    And the user selects "Inbound Call" from the Lead Source dropdown
    And the user click on the Lead Type dropdown and traverses 1st step
    And the user selects "1. Medical Practitioner" from the Eligibility ID dropdown
    And I select the PRCitizen checkbox
    And the user enters "15/06/1995" as the birth date
    And I generate and enter a random phone number
    And the user enters a random email
    And User types "123" in the suggestion input box and selects the 4 option using keyboard
    And the user selects "Cold" from the Agent Lead Score dropdown
    And the user click on the Eligibility details and select 1 'CMO' from the dropdown
    And the user click on the Gender dropdown and traverses 1 step
    And the user click on the how did you hear about us dropdown and selects 1 'DHF Sales Specialist' from the dropdown
    And the user navigates to the Previous Health Fund tab
    And the user click on the Previous Health Fund dropdown and select 2 'ACA Health Benifits Fund' from the dropdownn
    And the user click on the Previous Fund Transfer dropdown and traverses 2 step
    And I check the Cooling off checkbox
    And the user click on the 31st birthday dropdown and traverses 1 'Yes' step
    And I enter a random Health Fund Membership ID
    And I enter a policy name
    And I click on the Save button
    #And I search the user by entering the First&Last name
    And I click on the first user fullname in the table
    And I click on the Medicare button
    And I click on the Edit button
    And the user click on the medical card type dropdown and traverses 1 'Green' step
    And I enter the Medicare card number "2330743489"
    And the user click on the medical expiry month dropdown and traverses 5th step
    And the user click on the applicant claiming rebate dropdown and traverses 2nd step
    And the user click on the all people hold policy dropdown and traverses 1st step
    And I enter the Medicare card expiry year "2025"
    And I click on the Save button
    And I click on compliance tab
    And I click on the Edit button
    And I select the Hospital-extra Waiting Periods checkbox
    And I select the Exclusions-Restrictions checkbox
    And I select the Rebate checkbox
    And I select the LHC checkbox
    And I select the Excess Rules-Waiting Periods checkbox
    And I click on the Save button
    And I click on the Affiliation tab
    And I click on the Association
    And the user click on the association dropdown and traverses 1st step
    #And the user selects "Not applicable" from the association dropdown
    And I click on the Save button
    And I click on the Product
    And the user click on the class dropdown and traverses 1st step
    And the user click on the hospital dropdown and traverses 1st step
    And the user click on the extra dropdown and traverses 1st step
    And I click on the Financial
    And the user click on the payment frequency dropdown and traverses 1st step
    And I click on the calculate quote button
    And I click on the save_1 button
    And I capture the success message and close the popup
    And I click on the Financial Info
    And I click on the Edit button
    And The user clicks on the Group dropdown, enters the "direc" into the suggestion input box, and selects the 1 option using the keyboard.
    And the user click on the debit account type dropdown and traverses 2 step
    #And I enter the start date "02/12/2024" in the direct debit field
    And I enter the card number "4242424242424242" in the card number field
    And the user click on the card expiry month dropdown and traverses 3 step
    And I enter the cardholder name in the name field
    And I enter expiry year "2030" in the expiry year field
    And I successfully submit and save the credit card details
    Then I verify the temporary CRN number is generated and capture its value
    And I click on the Edit button
    And The user enters a random name in the Benefit Bank Account Name field
    And I enter the valid benefit BSB number "062-062"
    And The user enters "404647585" in the Benefit Bank Account Number field
    And I successfully save the Financial Information
    And I click on the Wrap Up Tab
    And I click on the Edit button
    And The user enters joining date
    And the user click on the welcome call dropdown and traverses 1st step
    And The user selects the Claiming Process checkbox
    And The user selects the OMS Registration checkbox
    And the user click on the authority level dropdown and traverses 2nd step
    And I click on the Save button
    And The user clicks on the Convert to Membership button
    Then I capture the membership creation message and validate the ID
