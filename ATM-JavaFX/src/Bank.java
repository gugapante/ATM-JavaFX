import java.util.ArrayList;

public class Bank {
    ArrayList<BankAccount> accounts = new ArrayList<>();

    public void addAccount(String accountName, String accountNumber, String accountPassword, double balance) {
        BankAccount newAccount = new BankAccount(accountName, accountNumber, accountPassword, balance);
        accounts.add(newAccount);
        System.out.println("Account " + accountName + " successfully added! " + accountNumber + ", " + accountPassword + ", $" + balance);
    }

    public boolean findAccount(String accountNumber, String accountPassword) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber) && account.getAccountPassword().equals(accountPassword)) {
                return true;
            }
        }
        return false;
    }

    public BankAccount setAccount(String accountNumber, String accountPassword) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber) && account.getAccountPassword().equals(accountPassword)) {
                return account;
            }
        }
        return null;
    }

    public ArrayList<BankAccount> getAccounts() {
        return accounts;
    }

}