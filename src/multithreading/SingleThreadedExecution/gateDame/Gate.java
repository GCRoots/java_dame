package multithreading.SingleThreadedExecution.gateDame;

/**
 * @author shipengfei
 * @data 19-9-26
 */
public class Gate {
    private int counter =0;
    private String name="Nobody";
    private String address="Nowhere";

    public synchronized void pass(String name,String address) throws InterruptedException {
        this.counter++;
        this.name=name;
        this.address=address;
        check();
        Thread.sleep(100);
    }

    private void check() {
        if (name.charAt(0) != address.charAt(0)){
            System.out.println(" ***** WARNING ***** "+toString());
        }else {
            System.out.println("NO." + counter + ":\t" + name +"\tAT  "+ address +"\tPASSING");
        }
    }

    @Override
    public String toString() {
        return "NO." + counter + ":\t" + name + "\t" + address;
    }
}
