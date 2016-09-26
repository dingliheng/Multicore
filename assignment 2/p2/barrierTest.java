/**
 * Created by Liheng on 9/25/16.
 */
public class barrierTest {
    static class myThread extends Thread{
        int id;
        CyclicBarrier cyclicBarrier;
        public void run() {
            try {
                System.out.println("p"+id+" is waiting");
                System.out.println("p"+id+" return "+cyclicBarrier.await()+" moves on");
            } catch (Exception e) {
                System.out.println("p"+id+" cannot wait");
            }
        }
    }

    public static void main(String[] strings) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        for(int i=0;i<4;i++) {
            myThread newThread = new myThread();
            newThread.id = i;
            newThread.cyclicBarrier = cyclicBarrier;
            newThread.start();
        }
    }
}
