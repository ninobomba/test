Feature:Todo Catalog
  As a user
  I want to retrieve the first id of my to-do activities
  So that I can verify next actions for the day
# Find
  Scenario:Display the first activity of the day #1
    Given A an end-point available to get my activities
    When I query this end-point
    Then It should return the first todo activity od the day
