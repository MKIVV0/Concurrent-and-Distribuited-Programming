package Bank;

public class BankCustomer extends Thread{
    private String firstName;
    private String lastName;
    private BankAccount bankAccount;
    private static int cnt = 0;
    private int iterations = 5;


    BankCustomer(String fn, String sn, BankAccount ba) {
        this.firstName = fn;
        this.lastName = sn;
        this.bankAccount = ba;
    }

    @Override
    public void run() {
        while (cnt < iterations) {
            if (Math.random() > 0.5) {
                this.bankAccount.withdraw( Math.random() * 1000, this.firstName + " " + this.lastName);
            }
            else {
                try {
                    this.bankAccount.deposit(Math.random() * 5000, this.firstName + " " + this.lastName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            addCount();
        }
    }

    private static synchronized void addCount() {
        ++cnt;
    }
}
