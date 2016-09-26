/**
 * Created by Liheng on 9/21/16.
 */

public class CountingSemaphore {
    int value;

    public CountingSemaphore(int initValue) {
        value = initValue;
    }

    public synchronized void P() throws InterruptedException{
        while (value == 0) wait();
        value--;

    }

    public synchronized void V() {
        value++;
        notify();
    }
}

