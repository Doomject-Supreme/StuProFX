package hdm.shared;

public class StopWatch {
    long startTime;

    /**
     * Creates a new stop clock and starts measuring elapsed time.
     */
    public StopWatch() {
        startTime = System.nanoTime();
    }

    /**
     * Returns time elapsed since creation of StopWatch or last reset, in
     * nanoseconds.
     * 
     * @return Elapsed time in nanoseconds.
     */
    public long getElapsedTime() {
        long now = System.nanoTime();
        return (now - startTime);
    }

    /**
     * Resets elapsed time of stop clock to 0.
     */
    public void reset() {
        startTime = System.nanoTime();
    }

    @Override
    public String toString() {
    	return String.format("Elapsed time: %,d s", getElapsedTime() / 1_000_000_000);
    }
}
