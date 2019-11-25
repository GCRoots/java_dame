package message_queue.ArrayBlockingQueueDemo;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author shipengfei
 * @data 19-11-25
 */
public class ProduceConsumeDemo {
    public static void main(String[] args) {
        ArrayBlockingQueue<Cookie> arrayBlockingQueue= new ArrayBlockingQueue<Cookie>(10);//生产者和消费者共用这一个队列，队列容量为10
        Produce produce = new Produce(arrayBlockingQueue);
        produce.start();
        //一个生产者，5个消费者
        new Thread(new Consume(arrayBlockingQueue)).start();
        new Thread(new Consume(arrayBlockingQueue)).start();
        new Thread(new Consume(arrayBlockingQueue)).start();
        new Thread(new Consume(arrayBlockingQueue)).start();
        new Thread(new Consume(arrayBlockingQueue)).start();

    }
}
