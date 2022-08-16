public class BankCustomer extends Thread{
    private String firstName;
    private String lastName;
    private BankAccount bankAccount;
    private static int cnt = 0;
    private static double totalFundsWithdrawn = 0;
    private static double totalFundsDeposited= 0;
    private int iterations = 100;


    BankCustomer(String fn, String sn, BankAccount ba) {
        this.firstName = fn;
        this.lastName = sn;
        this.bankAccount = ba;
    }

    @Override
    public void run() {
        while (cnt < iterations) {
            if (Math.random() > 0.5) {
                totalFundsWithdrawn += this.bankAccount.withdraw( Math.random() * 1000, this.firstName + " " + this.lastName);
            }
            else {
                try {
                    totalFundsDeposited += this.bankAccount.deposit(Math.random() * 5000, this.firstName + " " + this.lastName);
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

    public static double getTotFW() {
        return totalFundsWithdrawn;
    }

    public static double getTotFD() {
        return totalFundsDeposited;
    }
}
