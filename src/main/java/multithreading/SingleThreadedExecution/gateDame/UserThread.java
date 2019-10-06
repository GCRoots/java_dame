package multithreading.SingleThreadedExecution.gateDame;

/**
 * @author shipengfei
 * @data 19-9-26
 */
public class UserThread extends Thread{

    private final Gate gate;
    private final String myName;
    private final String myAddress;


    public UserThread(Gate gate, String myName, String myAddress) {
        this.gate = gate;
        this.myName = myName;
        this.myAddress = myAddress;
    }

    public void run(){
        System.out.println(myName+"\tAT  "+myAddress+"\tBEGINING");
        while (true){
            try {
                gate.pass(myName,myAddress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        System.out.println();

        Gate gate=new Gate();

        new UserThread(gate,"Alice","Alaska").start();
        new UserThread(gate,"Bobby","Brazil").start();
        new UserThread(gate,"Chris","Canada").start();


    }

}
