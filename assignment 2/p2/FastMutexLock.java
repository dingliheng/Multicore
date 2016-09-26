import java.util.concurrent.atomic.AtomicInteger;

// TODO
// Implement Fast Mutex Algorithm
class FastMutexLock implements MyLock {
    AtomicInteger x;
    AtomicInteger y;
    int N;
    private static AtomicInteger[] flag;

    public FastMutexLock(int numThread) {
        this.N = numThread;
        this.x = new AtomicInteger(-1);
        this.y = new AtomicInteger(-1);
        this.flag = new AtomicInteger[N];
        for (int i = 0; i < N; i++) {
            flag[i] = new AtomicInteger(0);
        }
    }

    @Override
    public void lock(int i) {
        while (true) {
            flag[i].set(1);
            x.set(i);
            if (y.get() != -1) {
                flag[i].set(0);
                while (y.get() != -1)
                    continue;
            } else {
                y.set(i);
                if (x.get() == i) {
                    return;
                } else {
                    flag[i].set(0);
                    for (int j = 0; j < N; j++) {
                        while (flag[j].get() != 0) ;
                    }
                    if (y.get() == i) return;
                    else {
                        while (y.get() != -1)
                            continue;
                    }
                }
            }
        }
    }

    @Override
    public void unlock(int i) {
        y.set(-1);
        flag[i].set(0);
    }
}
