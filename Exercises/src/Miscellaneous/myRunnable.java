package Miscellaneous;

public class myRunnable implements Runnable {

    private int id;
    private int count;

    public myRunnable(int id) {
        this.id = id;
        this.count = 1;
    }

    @Override
    public void run() {
        System.out.println("Hi! I'm thread no. " + this.id);
        while (count < 1000) {
            count += (int) (Math.random() * 100);
            System.out.println(this.id + " " + count);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        myRunnable ro1 = new myRunnable(1);
        myRunnable ro2 = new myRunnable(2);
        Thread t1 = new Thread(ro1);
        Thread t2 = new Thread(ro2);
        t1.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {}
        t2.start();
        t1.join();
        t2.join();
        System.err.println("All threads finished counting!");
    }
}
