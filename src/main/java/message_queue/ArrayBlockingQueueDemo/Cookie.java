package message_queue.ArrayBlockingQueueDemo;

/**
 * @author shipengfei
 * @data 19-11-25
 */
public class Cookie {
    private String number;
    public Cookie(String number){
        this.number=number;
    }
    @Override
    public String toString() {
        return number+"";
    }
}
