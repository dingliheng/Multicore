import java.util.concurrent.Semaphore;

/**
 * Created by Liheng on 9/21/16.
 */

public class CyclicBarrier {
    private static int n;
    private static int count;
    private static Semaphore s1;
    private static Semaphore s2;

    public CyclicBarrier(int parties){
        this.n = parties;
        this.count = 0;

        this.s1 = new Semaphore(1);
        this.s2 = new Semaphore(0);
    }

    int await() throws InterruptedException {
        s1.acquire();
        int res = ++this.count;
        if (this.count == this.n){
            for (int i=0; i< this.n; i++){
                this.s2.release();
            }
            this.count = 0;
        }
        this.s1.release();
        this.s2.acquire();
        return n-res;
    }
}
