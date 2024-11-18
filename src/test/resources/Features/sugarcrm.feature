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
    Then I should see the page title as "SugarCRM"
    And I click on the Leads button
    And I click on the Create button
    And I select salutation as "Mr"
