Feature: A new user account can be created if a proper unused username and password are given

    Scenario: creation is successful with valid username and password
        Given command new user is selected
        When  username "markku" and password "uusi12345" are entered
        Then  system will respond with "new user registered"
    
    Scenario: creation fails with already taken username and valid password
        Given command new user is selected
        When  username "pekka" and password "uusi123456" are entered
        Then  system will respond with "new user not registered"

    Scenario: creation fails with too short username and valid password
        Given command new user is selected
        When  username "a" and password "uusi1234" are entered
        Then  system will respond with "new user not registered"

    Scenario: creation fails with valid username and too short password
        Given command new user is selected
        When  username "jukka" and password "h" are entered
        Then  system will respond with "new user not registered"

    Scenario: creation fails with valid username and password enough long but consisting of only letters
        Given command new user is selected
        When  username "jussi" and password "koira" are entered
        Then  system will respond with "new user not registered"

    Scenario: can login with successfully generated account
        Given user "eero" with password "salasana2" is created
        And   command login is selected
        When  username "eero" and password "salasana2" are entered
        Then  system will respond with "wrong username or password"