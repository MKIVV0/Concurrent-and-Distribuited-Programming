package Miscellaneous;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Motor extends Thread{
    final static int ITERATIONS = 15_000;

    private int id;

    private int CURRENT_RPM;

    private CyclicBarrier barrier;

    public Motor(int id, CyclicBarrier b) {
        this.id = id;
        this.barrier = b;
        this.CURRENT_RPM = 0;
    }

    @Override
    public void run() {
        while (CURRENT_RPM <= ITERATIONS) {
            this.CURRENT_RPM += Math.random() * 2000;
            System.out.println(this.id + " current RPM: " + this.CURRENT_RPM);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){}
        }
        try {
            this.barrier.await();
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Motor[] m = new Motor[4];
        canFly cf = new canFly();
        CyclicBarrier barrier = new CyclicBarrier(4, cf);
        for (int i = 0; i < 4; i++) {
            m[i] = new Motor(i + 1, barrier);
            m[i].start();
        }
    }
}
