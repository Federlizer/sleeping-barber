import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;

public class CustomerQueue {
    private static CustomerQueue instance = new CustomerQueue();

    private ArrayBlockingQueue<Customer> queue;

    private CustomerQueue() {
        queue = new ArrayBlockingQueue<>(10, true);
    }

    public static CustomerQueue getInstance() {
        return instance;
    }

    public boolean hasNext() {
        return queue.peek() != null;
    }

    public boolean addCustomer(Customer c) {
        try {
            queue.add(c);
        } catch (IllegalStateException e) {
            return false;
        }
        System.out.printf("New queue size: %d\n", queue.size());
        return true;
    }

    public Customer getNextCustomer() throws NoSuchElementException {
        return queue.remove();
    }
}
