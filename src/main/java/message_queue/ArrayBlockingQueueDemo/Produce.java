package message_queue.ArrayBlockingQueueDemo;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author shipengfei
 * @data 19-11-25
 */
public class Produce extends Thread{

    private static int i=0;
    private ArrayBlockingQueue<Cookie> arrayBlockingQueue;

    public Produce(ArrayBlockingQueue<Cookie> arrayBlockingQueue){
        this.arrayBlockingQueue=arrayBlockingQueue;
    }

    public void run(){
        try {
            while (i<1000) {
                arrayBlockingQueue.put(new Cookie("cookie"+i));
                if (++i%100==0){//每生产100个，休息10s
                    Thread.sleep(10000);
                }
            }
        }catch (InterruptedException e){
            System.out.println("produce queue InterruptedException");
        }
    }
}
