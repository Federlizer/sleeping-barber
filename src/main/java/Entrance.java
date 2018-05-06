import java.util.Random;

public class Entrance implements Runnable {
    private CustomerQueue queue;
    private Thread barberThread;

    public Entrance(Thread barberThread) {
        queue = CustomerQueue.getInstance();
        this.barberThread = barberThread;
    }

    private Customer customerEnter() {
        try {
            Thread.sleep(new Random().nextInt(5) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Customer();
    }

    private void wakeBarber() {
        synchronized (barberThread) {
            barberThread.notify();
        }
    }

    @Override
    public void run() {
        while (barberThread.isAlive()) {
            Customer c = customerEnter();
            if (barberThread.getState() == Thread.State.WAITING) {
                queue.addCustomer(c);
                System.out.println("Waking barber");
                wakeBarber();
            }
            boolean entered = queue.addCustomer(c);
            if (!entered) {
                System.out.println("Queue is full, customer leaving");
            }
        }
    }
}
