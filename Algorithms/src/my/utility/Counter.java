package my.utility;

/**
 * 
 * {@summary Counting number for multi-threading}
 * 
 * @author Jiale Hu
 *
 */
public class Counter {
    private int count;

    public Counter() {
	count = 0;
    }

    /**
     * 
     * {@summary Count once}
     * 
     * @implNote {@code Synchronized}
     */
    public synchronized void count() {
	count++;
    }

    /**
     * 
     * @return Number of Total Count
     */
    public int getCount() {
	return count;
    }

    /**
     * 
     * {@summary Reset counter to zero}
     */
    public void reset() {
	count = 0;
    }
}
