package rpc.grpc.examples;

/**
 * @author shipengfei
 * @data 19-10-11
 */
public class cycle {
    private double r=1; //半径
    private double l=6.28; //周长
    private double s=3.14; //面积

    public double getR() {
        return r;
    }

    public double getL() {
        return l;
    }

    public double getS() {
        return s;
    }

    public void setR(double r) {
        this.r = r;
    }

    public void setL(double l) {
        this.l = l;
    }

    public void setS(double s) {
        this.s = s;
    }

    public cycle(double r) {
        this.r = r;
        this.l=6.28*r;
        this.s=3.14*r*r;
    }

    public cycle() {
    }

    public static void main(String[] args) {

        cycle c=new cycle();
        System.out.println(c.getR()+"\t"+c.getL()+"\t"+c.getS());

        cycle c1=new cycle(2);
        System.out.println(c1.getR()+"\t"+c1.getL()+"\t"+c1.getS());

    }
}
