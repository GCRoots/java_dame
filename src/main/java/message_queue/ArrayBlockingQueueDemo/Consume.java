package message_queue.ArrayBlockingQueueDemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author shipengfei
 * @data 19-11-25
 */
public class Consume  implements Runnable{
    private ArrayBlockingQueue<Cookie> arrayBlockingQueue;

    public Consume(ArrayBlockingQueue<Cookie> arrayBlockingQueue){
        this.arrayBlockingQueue=arrayBlockingQueue;
    }

    public void run(){
        try{
            while (true){
                Cookie poll = arrayBlockingQueue.poll(5, TimeUnit.SECONDS);//如果queue为null，那么5秒之后再去队列中取数据
                if (poll!=null)
                    System.out.println(Thread.currentThread().getName()+"--consume --"+poll);

            }
        }catch (InterruptedException e){
            System.out.println("consume queue InterruptedException");
        }
    }
}