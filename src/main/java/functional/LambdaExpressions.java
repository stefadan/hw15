package functional;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaExpressions {
    public static void main(String[] args) {
        tryFunction(a -> a + 1);
        tryFunction(a -> a * 10);

        trySupplier(() -> "Hello");
        trySupplier(() -> "World");

        tryPredicate(p -> p < 10);
        tryPredicate(i -> (i % 2 == 0));

        tryConsumer(s-> System.out.println(s.toUpperCase()));

            tryMultiple(
                    ()->"Fast",
                    s->s.length(),
                    numar->(numar>5),
                    numar-> System.out.println("Number greater than 5")
            );
    }

    public static void tryMultiple(
            Supplier<String> supplier,
            Function<String, Integer> transformer,
            Predicate<Integer> tester,
            Consumer<Integer> consumer
    ){
        String s = supplier.get();
        Integer integer = transformer.apply(s);
        if (tester.test(integer)){
            consumer.accept(integer);
        }
        else{
            System.out.println("Not good");
        }

    }

    public static void tryConsumer(Consumer<String>  consumer){
        consumer.accept("somthing");
        consumer.accept("something else");
    }

    public static void tryPredicate(Predicate<Integer> predicateTester) {
        int a = 22;
        if (predicateTester.test(a)) {
            System.out.println("win");
        } else {
            System.out.println("lost");
        }
    }

    public static void tryFunction(Function<Integer, Integer> incrementor) {
        System.out.println(incrementor.apply(4));

    }

    public static void trySupplier(Supplier<String> supplier) {
        String s = supplier.get();
        if (s.startsWith("H")) {
            System.out.println(s.toLowerCase());
        } else {
            System.out.println("Not starting with H");
        }
    }
}
