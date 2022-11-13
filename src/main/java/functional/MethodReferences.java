package functional;

import java.util.function.Function;

public class MethodReferences {
    public static void main(String[] args) {

        printSomethingInACertainWay( s->s.toLowerCase());

        printSomethingInACertainWay( s-> s.toUpperCase());

    }

    public static void printSomethingInACertainWay(Function<String, String> howToPrint){
        System.out.println(howToPrint.apply("hello"));
    }

}
