package threads;

public class Semaphore {

//rename
     Semaphore semaphore;

     private int counter;
     private volatile boolean isCleaned;

     public Semaphore(int value) {
         counter = value;
     }

    public synchronized void clean(int i) {
        while(isCleaned)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        System.out.println("Cleaning room number " + i);
        isCleaned = true;
        notifyAll();
    }

    public synchronized void repair(int i) {
        while(!isCleaned)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        System.out.println("Repairing room number " + i);
        isCleaned = false;
        notifyAll();
    }
}
