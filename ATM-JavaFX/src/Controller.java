import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnClr, btnEnt;

    @FXML
    private Button withdrawBtn, depositBtn, balanceBtn, addAccountBtn, logoutBtn, exitBtn;

    @FXML
    private TextField userInput;

    @FXML
    private TextArea userResult;

    public void onButtonPress(ActionEvent event) {
        String ButtonId =((Button)event.getSource()).getId();

        switch(ButtonId){
            case "btn0":
                userInput.appendText("0");
                break;
            case "btn1":
                userInput.appendText("1");
                break;
            case "btn2":
                userInput.appendText("2");
                break;
            case "btn3":
                userInput.appendText("3");
                break;
            case "btn4":
                userInput.appendText("4");
                break;
            case "btn5":
                userInput.appendText("5");
                break;
            case "btn6":
                userInput.appendText("6");
                break;
            case "btn7":
                userInput.appendText("7");
                break;
            case "btn8":
                userInput.appendText("8");
                break;
            case "btn9":
                userInput.appendText("9");
                break;
            case "btnClr":
                userInput.clear();
                break;
            case "btnEnt":
                handleEnter();
                break;
            case "withdrawBtn":
                handleWithdraw();
                break;
            case "depositBtn":
                handleDeposit();
                break;
            case "balanceBtn":
                handleBalance();
                break;
            case "addAccountBtn":
                handleAddAccount();
                break;
            case "logoutBtn":
                handleLogout();
                break;
            case "exitBtn":
                handleExit();
                break;
        }
    }

    final String ACCOUNT_NUM = "account_num";
    final String PASSWORD = "password";
    final String LOGGED_IN = "logged_in";
    final String ADD_ACCOUNT = "adding_account";
    final String ACCOUNT_DISPLAY = "account_display";

    String state = ACCOUNT_NUM;

    // This is purely for debugging purposes and can be removed later
    public void setState(String newState){
        if ( !state.equals(newState) )
        {
            String oldState = state;
            state = newState;
            System.out.println("Changed from "+oldState+" to "+newState);
        }
    }

    private Bank bank;
    private BankAccount bankAcc;
    private String accountName;
    private String accountNumber;
    private double balance;

    @FXML
    public void initialize(){
        bank = new Bank();

        // Debugging purposes
        System.out.println("Initialising");
        System.out.println("Bank instance created");

        setState(ACCOUNT_NUM);
        displayMessage();

        // Tester accounts so that there is something to test with
        bank.addAccount("Gus De Sousa", "1234", "0000", 500);
        bank.addAccount("Alin Pacurar", "5678", "1111", 1000);
    }

    public void displayMessage(){
        userResult.setText("Welcome, enter an account number " +
                "or exit by pressing the 'EXIT' button...");
    }

    public void handleEnter(){
        String input = userInput.getText();
        switch(state){
            case ACCOUNT_NUM:
                accountNumber = input;
                userInput.clear();
                setState(PASSWORD);
                userResult.setText("Please enter your Password: ");
                break;
            case PASSWORD:
                String accountPassword = input;
                userInput.clear();
                if(bank.findAccount(accountNumber, accountPassword)){
                    bankAcc = bank.setAccount(accountNumber, accountPassword);
                    accountName = bankAcc.getAccountName();
                    setState(LOGGED_IN);
                    userResult.setText("Login Successful!\n" +
                            "Welcome "+accountName+"!\n"+
                            "Please enter an amount followed " +
                            "by an operator key or logout by " +
                            "pressing the 'L/O' button...");
                } else {
                    userInput.clear();
                    userResult.setText("Account or Password do not match. " +
                            "Please enter account number again...");
                    setState(ACCOUNT_NUM);
                }
                break;
            case LOGGED_IN:
                break;
            case ADD_ACCOUNT:
                handleAddAccount();
                userInput.clear();
                if(input.matches("[a-zA-Z ]+")){
                    bankAcc = new BankAccount("", "", "", 0);
                    System.out.println("Bank account created");
                    bankAcc.setAccountName(input);
                    accountName = bankAcc.getAccountName();
                    userInput.clear();

                    int newAccNum = (int)(Math.floor(Math.random() * (9999 - 1000 + 1) + 1000));
                    int newAccPass = (int)(Math.floor(Math.random() * (9999 - 1000 + 1) + 1000));
                    userResult.setText("Hello "+accountName+"!\n"+
                            "\n"+ "New account number: " +newAccNum+ "\n" +
                            "New account password: " +newAccPass+ "\n" +
                            "\n"+ "Please press 1 then enter to continue...");

                    String accAsString = Integer.toString(newAccNum);
                    String passAsString = Integer.toString(newAccPass);

                    bankAcc.setAccountNumber(accAsString);
                    bankAcc.setAccountPassword(passAsString);
                    setState(ACCOUNT_DISPLAY);
                }
                break;
            case ACCOUNT_DISPLAY:
                if(input.equals("1") && input.matches("[0-9]+")){
                    userInput.clear();
                    accountNumber = bankAcc.getAccountNumber();
                    accountPassword = bankAcc.getAccountPassword();
                    balance = bankAcc.getBalance();
                    bank.addAccount(accountName, accountNumber, accountPassword, balance);
                    userResult.setText("You're all set!\n" +
                            "\n"+ "Account Name: "+accountName+"\n"+
                            "Account #: "+accountNumber+"\n" +
                            "Password: "+ accountPassword +"\n" +
                            "Balance: $"+balance+"\n" +
                            "\n"+ "- Enter amount followed by 'DEP' to deposit\n" +
                            "- Select 'L/O' to logout");
                    setState(LOGGED_IN);
                } else {
                    userInput.clear();
                    userResult.setText("Oops, something went wrong\n" +
                            "Please try again!");
                    setState(ADD_ACCOUNT);
                }
                break;
        }
    }

    public void handleWithdraw(){
        double amount = Double.parseDouble(userInput.getText());
        if(state.equals(LOGGED_IN)){
            if(bankAcc == null){
                userResult.setText("Error: No account logged in");
            } else if(bankAcc.withdraw(amount)){
                balance = bankAcc.getBalance();
                userResult.setText("Withdrawn: $"+amount+
                        "Your remaining balance is: $"+balance+"\n");
                userInput.clear();
            } else {
                userResult.setText("You have insufficient funds!");
            }
        } else {
            userResult.setText("Error: Please log in or create an account first");
            userInput.clear();
            setState(ACCOUNT_NUM);
        }
    }

    public void handleDeposit(){
        double amount = Double.parseDouble(userInput.getText());
        if(state.equals(LOGGED_IN)){
            if(bankAcc == null){
                userResult.setText("Error: No account logged in!");
            }else if(bankAcc.deposit(amount)){
            balance = bankAcc.getBalance();

            for (int i = 0; i < bank.getAccounts().size(); i++) {
                if (bank.getAccounts().get(i).getAccountNumber().equals(bankAcc.getAccountNumber()) &&
                        bank.getAccounts().get(i).getAccountPassword().equals(bankAcc.getAccountPassword())) {
                    bank.getAccounts().set(i, bankAcc);
                }
            }

            userResult.setText("Deposited: $"+amount+"\n" +
                    "Your new balance is: $"+balance+"\n");
            userInput.clear();
            } else {
                userResult.setText("Please enter a valid amount!");
                userInput.clear();
                setState(LOGGED_IN);
            }
        } else {
            userResult.setText("Error: Please log in or create an account first");
            userInput.clear();
            setState(ACCOUNT_NUM);
        }
    }

    public void handleBalance(){
        if(state.equals(LOGGED_IN)){
            accountNumber = bankAcc.getAccountNumber();
            if(bankAcc == null){
                userResult.setText("Error: No account logged in!");
            } else{
                balance = bankAcc.getBalance();
                userResult.setText("Account: "+accountNumber+"\n" +
                        "Current Balance: $"+balance+"\n");
            }
        } else {
            userResult.setText("Error: Please log in or create an account first");
            userInput.clear();
            setState(ACCOUNT_NUM);
        }
    }

    public void handleAddAccount(){
        userResult.setText("Please type in your fullname " +
                "followed by the 'ENT' button...");
        setState(ADD_ACCOUNT);
    }

    public void handleLogout(){
        setState(ACCOUNT_NUM);
        System.out.println("Logging out");
        userResult.setText("Welcome, please enter your account number:");
    }

    public void handleExit(){
        Platform.exit();
    }
}