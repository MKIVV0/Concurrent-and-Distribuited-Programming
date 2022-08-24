package Bank;

public class BankAccount {
    private double funds;
    private String name;
    private BankAccountType bat; 
    private double totalFundsWithdrawn;
    private double totalFundsDeposited;

    BankAccount(double money, String name, BankAccountType bat) {
        this.funds = money;
        this.name = name;
        this.bat = bat;
        this.totalFundsDeposited = 0;
        this.totalFundsWithdrawn = 0;
    }

    public synchronized double withdraw(double sum, String name) {
        if (this.funds - sum < 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.funds -= sum;
        this.totalFundsWithdrawn += sum;
        System.out.format("%s: %.2f withdrawn from %s's %s account\n", name, sum, this.name, this.bat);

        return sum;
    }

    public synchronized double deposit(double sum, String name) throws Exception {
        if (sum < 0) throw new Exception("Negative sum");
        this.funds += sum;
        this.totalFundsDeposited += sum;
        System.out.format("%s: %.2f deposited in %s's %s account\n", name, sum, this.name, this.bat);
        notifyAll();

        return sum;
    }

    public double getFunds() {
        return this.funds;
    }

    public void getInfo() {
        System.err.format("\n\n%s's account: \n", this.name);
        System.err.format("Total funds withdrawn: %.2f\n", this.totalFundsWithdrawn);
        System.err.format("Total funds deposited: %.2f\n", this.totalFundsDeposited);
        System.err.format("Available funds: %.2f", this.getFunds());
    }
}
