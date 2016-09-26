// TODO
// Implement the bakery algorithm

import java.util.concurrent.atomic.AtomicInteger;

public class BakeryLock implements MyLock {
    int N;
    AtomicInteger[] choosing; // inside doorway
    AtomicInteger[] number;

    public BakeryLock(int numThread) {
        N = numThread;
        choosing = new AtomicInteger[N];
        number = new AtomicInteger[numThread];
        for (int j = 0; j < N; j++) {
            choosing[j] = new AtomicInteger(0);
            number[j] = new AtomicInteger(0);
        }
    }

    @Override
    public void lock(int i) {
        // step 1: doorway: choose a number
        choosing[i].set(1);
        for (int j = 0; j < N; j++)
            if (number[j].get()> number[i].get())
                number[i].set(number[j].get());
        number[i].addAndGet(1);
        choosing[i].set(0);

        // step 2: check if my number is the smallest
        for (int j = 0; j < N; j++) {
            while (choosing[j].get()==1) ; // process j in doorway
//            System.out.println(((number[j].get()==0)));
            while ((number[j].get()!=0) &&
            ((number[j].get() < number[i].get()) ||
                    (((number[j].get()==number[i].get())) && j < i)))
                ; // busy wait
        }
    }

    @Override
    public void unlock(int i) {
        number[i].set(0);
    }

}