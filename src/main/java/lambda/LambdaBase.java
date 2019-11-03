package lambda;


import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author shipengfei
 * @data 19-10-27
 */
public class LambdaBase {

    public static void main(String[] args) {
        Function<Integer,Integer> f= p->p+1;
        f.apply(1);
        Arrays.asList(1,2,3).stream().map(f).forEach(System.out::println);

        System.out.println("===========================================================");

        BiFunction<Integer,Integer,Integer> b=(x,y)->x+y;
        b.apply(1,2);
        BinaryOperator<Integer> b1= (x, y)->x+y;//BinaryOperator是BiFunction的子类
        System.out.println(Arrays.asList(1,2,3).stream().reduce(b1).get());

        System.out.println("===========================================================");

        Consumer<Integer> c=p-> System.out.println(p);


    }

}
