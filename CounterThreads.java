public class CounterThreads {

    static class CountUpThread extends Thread {
        public void run() {
            for (int count = 1; count <= 20; count++) {
                System.out.println(
                    "Thread 1 - Counting up: " + count
                );

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println(
                        "Count-up thread was interrupted."
                    );
                    return;
                }
            }

            System.out.println("Thread 1 finished at 20.");
        }
    }

    static class CountDownThread extends Thread {
        public void run() {
            for (int count = 20; count >= 0; count--) {
                System.out.println(
                    "Thread 2 - Counting down: " + count
                );

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println(
                        "Count-down thread was interrupted. Please try again"
                    );
                    return;
                }
            }

            System.out.println("Thread 2 finished at 0.");
        }
    }

    public static void main(String[] args) {
        CountUpThread thread1 = new CountUpThread();
        CountDownThread thread2 = new CountDownThread();

        try {
            System.out.println("Starting the counter application.");

            thread1.start();
            thread1.join();

            System.out.println(
                "Thread 1 completed. Starting Thread 2."
            );

            thread2.start();
            thread2.join();

            System.out.println("Both threads completed.");
        } catch (InterruptedException e) {
            thread1.interrupt();
            thread2.interrupt();
            Thread.currentThread().interrupt();

            System.err.println(
                "Main thread was interrupted. Application stopping."
            );
        }
    }
}