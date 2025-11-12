public class BankAccount
{
    private String accountName;
    private String accountNumber;
    private String accountPassword;
    private double balance;

    // Constructor
    public BankAccount(String accountName, String accountNumber, String accountPassword, double balance){
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.accountPassword = accountPassword;
        this.balance = balance;
    }

    // Getters and setters
    public String getAccountName(){
        return accountName;
    }

    public void setAccountName(String accountName){
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber){
        this.accountNumber = accountNumber;
    }

    public String getAccountPassword(){
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword){
        this.accountPassword = accountPassword;
    }


    // For debugging purposes
    @Override
    public String toString() {
        return "Account Number: "+accountNumber+", Balance: £"+balance;
    }

    public boolean withdraw(double amount){
        if(amount > 0 && amount <= this.balance){
            this.balance -= amount;
            return true;
        }
        return false;
    }

    public boolean deposit(double amount){
        if(amount > 0){
            this.balance += amount;
            return true;
        }
        return false;
    }

    public double getBalance(){
        return this.balance;
    }
}

