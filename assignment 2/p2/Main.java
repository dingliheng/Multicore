public class Main {
    public static void main(String[] args) {
        Counter counter = null;
        MyLock lock = null;
        long executeTimeMS = System.nanoTime();
        int numThread = 6;
        int numTotalInc = 12;

        if (args.length < 3) {
            System.err.println("Provide 3 arguments");
            System.err.println("\t(1) <algorithm>: fast/bakery/synchronized/"
                    + "reentrant");
            System.err.println("\t(2) <numThread>: the number of test thread");
            System.err.println("\t(3) <numTotalInc>: the total number of "
                    + "increment operations performed");
            System.exit(-1);
        }

        numThread = Integer.parseInt(args[1]);
        numTotalInc = Integer.parseInt(args[2]);

        if (args[0].equals("fast")) {
            lock = new FastMutexLock(numThread);
            counter = new LockCounter(lock);
        } else if (args[0].equals("bakery")) {
            lock = new BakeryLock(numThread);
            counter = new LockCounter(lock);
        } else {
            System.err.println("ERROR: no such algorithm implemented");
            System.exit(-1);
        }

        // TODO
        // Please create numThread threads to increment the counter
        // Each thread executes numTotalInc/numThread increments
        // Please calculate the total execute time in millisecond and store the
        // result in executeTimeMS
        myThreads[] threads = new myThreads[numThread];
        for (int i = 0; i < numThread; i++) {
            threads[i] = new myThreads(i, numTotalInc / numThread, counter, lock);
            threads[i].start();
        }
        for (int j = 0; j < numThread; j++) {
            try {
                threads[j].join();
            } catch (Exception e) {}
        }
        executeTimeMS = (System.nanoTime() - executeTimeMS) / 1000000;
        System.out.println(counter.count);
        System.out.println(executeTimeMS);
    }

    static class myThreads extends Thread {
        int id;
        int times;
        Counter counter;
        MyLock lock;

        public myThreads(int _id, int _times, Counter _counter, MyLock _lock) {
            this.id = _id;
            this.times = _times;
            this.counter = _counter;
            this.lock = _lock;
        }

        public void run() {
            while (times > 0) {
                lock.lock(id);
                counter.increment();
//                System.out.println(id+" "+counter.count);
                lock.unlock(id);
                times--;
            }
        }
    }
}
