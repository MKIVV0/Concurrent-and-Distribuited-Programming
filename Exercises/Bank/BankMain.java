public class BankMain {
    public static void main(String[] args) {
        BankAccount ba = new BankAccount(10000, "Smith", BankAccountType.FAMILY);
        BankCustomer bc1 = new BankCustomer("John", "Smith", ba) ;
        BankCustomer bc2 = new BankCustomer("Ellie", "Smith", ba);
        BankCustomer bc3 = new BankCustomer("Brandon", "Smith", ba);

        BankAccount ba1 = new BankAccount(5000, "Rachel Johnson", BankAccountType.PERSONAL);
        BankCustomer bc4 = new BankCustomer("Rachel", "Johnson", ba1);

        bc1.start();
        bc2.start();
        bc3.start();
        bc4.start();
        try {
            bc1.join();
            bc2.join();
            bc3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ba.getInfo();
        ba1.getInfo();

    }
}
