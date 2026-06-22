# ATM-JavaFX
Created an ATM application using JavaFX as part of a team for my Programming and Data Structures university module.

# Introduction
<p align = "justify">
The initial scripts given lacked key ATM functions such as deposit, withdrawal, and balance checking. These were identified as necessary improvements to make the application fully functional. The original interface was manually coded, making it more complex and harder to maintain, so Scene Builder was introduced to simplify UI design and improve readability.
<br>
<br>
The button layout was also redesigned to better match a real ATM, with controls positioned on either side of the screen for a more familiar user experience. Additional features were planned, including saving accounts after logout, creating new accounts, and generating random account numbers and passwords.
<br>
<br>
To store account data, an 'ArrayList' was chosen for simplicity, along with methods to add accounts and retrieve account details by iterating through the list. The project structure was organised into key files: ATM-Application, Controller, Bank, and Bank-Account. Most of the original codebase was reused with modifications, and an FXML file linked to Scene builder was used instead of separate media or view files.
</p>

# Design and Development
## Scene Builder
<p align = "justify">
The ATM application uses four main files: ATM-Application, Controller, Bank and Bank-Account. Most of the original code was reused and we scraped the media and view files in favour of Scene Builder using an FXML file.
<br>
<br>
An empty FXML file was created and opened in Scene Builder, where the GUI was built using drag-and-drop components like buttons and text fields placed inside an Anchor Pane. The FXML file was then linked to a Controller class created in IntelliJ.
<br>
<br>
<p align = "center">
  <img width="629" height="382" alt="image" src="https://github.com/user-attachments/assets/99446a70-f5df-4b27-99a8-50576a7e9196" />
  <br>
  <em align = "center"><b>Scene builder GUI for ATM Application</b></em>
</p>

Each UI Element was assigned a unique ID and event handler in Scene Builder so the application could control button actions. Additional code was added to ensure the buttons and text fields could be accessed and managed properly by the Java Application.

<p align = "center">
  <img width="699" height="363" alt="image" src="https://github.com/user-attachments/assets/79ba0d1c-44fc-4a77-9551-d14f31fbc1f3" />
  <br>
  <em align = "center"><b>FXML file with all the buttons and text fields</b></em>
</p>
</p>

# Controller Class
<p align = "justify">
The controller class uses an 'onButtonPress' method with a switch statement to determine what action to take depending on which button is pressed. It retrieves the event source, casts it to a button, and uses the button ID to identify the input. Number buttons append values to the input field, while action buttons like deposit and withdraw trigger specific methods.
</p>

<p align = "center">
    <img width="380" height="126" alt="image" src="https://github.com/user-attachments/assets/b19aacb2-e67f-444b-8e28-41c2bbfc2e1d" />
  <br>
  <em align = "center"><b>onButtonPress method</b></em>
</p>
  
<p align = "justify">
The program uses different "States" (e.g., login or account entry) to control application flow, extending the original design. Key variables are declared as private for encapsulation and used across multiple methods such as deposit and withdrawal functions.
</p>

<p align = "center">
  <img width="221" height="206" alt="image" src="https://github.com/user-attachments/assets/0926b713-0f1c-4299-8b8b-fb7fd08b103f" />
  <br>
  <em align = "center"><b>Buttons to handle enter, withdraw and deposit</b></em>
</p>

<p align = "center">
  <img width="409" height="144" alt="image" src="https://github.com/user-attachments/assets/dea45e47-09d6-4d8c-8f46-428ee150c5a8" />
  <br>
  <em align = "center"><b>States for the application</b></em>
</p>

<p align = "justify">
An 'Initialize()' method sets up the application by loading the FXML components, creating a Bank object, setting the initial state, displaying a welcome message, and adding test bank accounts for testing purposes.
</p>

<p align = "center">
  <img width="505" height="436" alt="image" src="https://github.com/user-attachments/assets/9c08c88d-9d36-4e34-97f9-86b0e1130f24" />
  <br>
  <em align = "center"><b>Instantiating the application</b></em>
</p>

## Handling Enter
<p align = "center">
  <img width="484" height="463" alt="image" src="https://github.com/user-attachments/assets/86c4469a-1583-481c-9c05-ac0b0fb389b0" />
  <br>
  <em align = "center"><b>Handle enter method with switch statement</b></em>
</p>

<p align = "justify">
The 'handleEnter' method controls most of the application flow using a switch statement based on program states.
<br>
<br>
In the 'ACCOUNT_NUM' state, the user enters their account number, then the state changes to 'PASSWORD'. The password is checked against stored accounts using 'findAccounts()'. If valid, the account is loaded with 'setAccount()'; otherwise, an error is shown and the user is returned to the login state.
</p>

<p align = "center">
  <img width="484" height="123" alt="image" src="https://github.com/user-attachments/assets/9b376a92-d54f-449d-9634-10794adf506a" />
  <br>
  <em align = "center"><b>Handle add account method</b></em>
</p>

<p align = "justify">
The 'ADD_ACCOUNT' state is triggered by the add button and runs 'handleAddAccount()', prompting the user to enter a name. Input is validated to ensure it contains only letters. A new bank account is then created with a randomly generated 4-digit account number.
</p>

<p align = "center">
  <img width="464" height="354" alt="image" src="https://github.com/user-attachments/assets/5ab815a0-5e79-479a-8aa0-7e3126a6c7fa" />
  <br>
  <em align = "center"><b>Adding account swithc statement</b></em>
</p>

<p align = "justify">
Finally, the state changes to 'ACCOUNT_DISPLAY', where the user is shown their new account details and prompted to proceed to login and start using the account.
</p>

<p align = "center">
  <img width="446" height="332" alt="image" src="https://github.com/user-attachments/assets/f5c6c025-4985-4431-86cf-e2ee5c20ea93" />

  <br>
  <em align = "center"><b>Account display state</b></em>
</p>

## Handle Withdraw
<p align = "justify">
If the user is in a logged in state, then the handle withdraw method will be called when the user enters an amount and presses the withdraw button on the GUI.
<br>
<br>
It starts by parsing the input from a string to a double and then checks to see if the user is in a logged in state. If yes, then it will check if the bank account is not null and if so, prompt the user to log in. The else if statement calls the withdraw method from the BankAccount class and passes the amount entered by the user as the argument.
</p>

<p align = "center">
<img width="514" height="299" alt="image" src="https://github.com/user-attachments/assets/702df23f-8da2-4a5c-bfcd-419d1f66a985" />
  <br>
  <em align = "center"><b>Handle withdraw method</b></em>
</p>

## Handle Deposit
<p align = "justify">
When the user is logged in and enters an amount, pressing the deposit button converts the input from a string to a double and checks that the user is in a valid logged-in state. If not, an error message is shown. It also verifies that a bank account exists before proceeding.
</p>

<p align = "center">
<img width="585" height="370" alt="image" src="https://github.com/user-attachments/assets/99f313d3-0ed0-46c0-a789-954628d5e65e" />
  <br>
  <em align = "center"><b>Handle deposit method</b></em>
</p>

<p align = "justify">
If valid, the deposit method in 'BankAccount' is called and the balance is updated, then retrieved and stored for display.
</p>

<p align = "center">
<img width="764" height="142" alt="image" src="https://github.com/user-attachments/assets/5089bab9-7a7d-4eb1-8ea8-188be8b475d5" />
  <br>
  <em align = "center"><b>For loop to update account balance</b></em>
</p>

<p align = "justify">
A bug was fixed where deposits were not being saved after logout and re-login because only the current object was updated, not the account stored in the 'ArrayList'. This was resolved by looping through the list, finding the matching account using account number and password, and updating the correct entry in the list so the new balance persists.
</p>

# Bank Account Class
<p align = "justify">
The class was simplified by introducing a constructor to reduce repeated code and improve efficiency when handling multiple bank accounts
</p>

<p align = "center">
<img width="612" height="203" alt="image" src="https://github.com/user-attachments/assets/bccc3e7d-282b-47a1-87c3-c42d058e5cb1" />
  <br>
  <em align = "center"><b>Constructor method for bank account object</b></em>
</p>

<p align = "justify">
Private variables were used for encapsulation, so getters and setters were added to allow controlled access and modification of account details like account number and password. These methods are public so they can be used by other classes.
</p>

<p align = "center">
<img width="616" height="208" alt="image" src="https://github.com/user-attachments/assets/7cb47cab-fc34-4d84-9636-6468d6a7cce1" />
  <br>
  <em align = "center"><b>Getter and setters for account number and password</b></em>
</p>

<p align = "justify">
The withdraw method checks that the amount is valid (greater than zero and not exceeding the balance) before subtracting it and returning true. The deposit method checks that the amount is greater than zero before adding it to the balance. The 'getBalance' method simply returns the current account balance.
</p>

<p align = "center">
<img width="386" height="398" alt="image" src="https://github.com/user-attachments/assets/7d38d27c-c592-45b3-85c3-e3badb4b9c4a" />
  <br>
  <em align = "center"><b>Withdraw, deposit and 'getBalance' methods</b></em>
</p>

# Bank Class
<p align = "justify">
The original fixed-size array (limited to 10 accounts) was replaced with an 'ArrayList' to allow unlimited account storage. The 'ArrayList' was created to store 'BankAccount' objects, and a public 'addAccount' method was added to create a new account using a constructor and store it in the list.
</p>

<p align = "center">
<img width="778" height="154" alt="image" src="https://github.com/user-attachments/assets/542c4587-0121-4b94-b167-5f3a4660e3c9" />
  <br>
  <em align = "center"><b>Add account 'ArrayList'</b></em>
</p>

<p align = "justify">
A 'findAccount' method uses a for-each loop to search the list by matching both account number and password, returning 'true' when a valid account is found for login security. Another similar method returns the full 'BankAccount' object so account details can be accessed after login.
</p>

<p align = "center">
<img width="777" height="145" alt="image" src="https://github.com/user-attachments/assets/e7586cdb-5bcb-4de5-84dd-2a26cc5cdd72" />
  <br>
  <em align = "center"><b>A method to find an account with matching number and password</b></em>
</p>

<p align = "justify">
A getter for the 'ArrayList' was also added to allow access to all accounts, which helped fix an issue where balances were not updating correctly after logout and re-login.
</p>

<p align = "center">
<img width="779" height="149" alt="image" src="https://github.com/user-attachments/assets/b01e83f8-33f1-41b9-aff2-1ecb5d6260c9" />
  <br>
  <em align = "center"><b>Method to set the account when logging in</b></em>
</p>

<p align = "center">
<img width="424" height="67" alt="image" src="https://github.com/user-attachments/assets/9c6ddbb5-73ac-4ae0-8872-dba3c14140b4" />
  <br>
  <em align = "center"><b>Getter for the BankAccount 'ArrayList'</b></em>
</p>
