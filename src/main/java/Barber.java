public class Barber implements Runnable {
    private CustomerQueue queue;
    private Thread t;

    public Barber() {
        queue = CustomerQueue.getInstance();
    }

    public void setThread(Thread t) {
        this.t = t;
    }

    private void goToSleep() {
        try {
            synchronized (t) {
                t.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            if (queue.hasNext()) {
                System.out.println("Cutting next customer's hair");
                queue.getNextCustomer().cutHair();
            }
            else {
                System.out.println("No more customers, going to sleep");
                goToSleep();
            }
        }
    }
}
