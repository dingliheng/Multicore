// TODO
// Use MyLock to protect the count

public class LockCounter extends Counter {
    MyLock myLock;
    public LockCounter(MyLock lock) {
        myLock = lock;
    }

    @Override
    public void increment() {
        count++;
//        System.out.println(count);
    }

    @Override
    public int getCount() {
        return count;
    }
}
