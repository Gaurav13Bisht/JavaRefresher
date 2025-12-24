package general;

import java.util.function.*;

public class FunctionalInterfacesImpl {
    public static void main(String[] args) {

        // Giving definition using Lambda expression, also we can create a class implementing this Predicate interface and give the definition for the method
        Predicate<Integer> isEven = x -> x % 2 == 0;
        System.out.println(isEven.test(6));

        Function<Integer, Integer> timesThree = x -> x * 3;
        System.out.println(timesThree.apply(4));

        Consumer<Integer> printElement = x -> System.out.println(x);
        printElement.accept(34);

        Supplier<Integer> giveRandomValue = () -> (int) (Math.random() * 100) % 16;
        System.out.println(giveRandomValue.get());

        BiPredicate<Integer, Integer> sumEven = (x, y) -> (x + y) % 2 == 0;
        System.out.println(sumEven.test(2, 6));

        BiFunction<Integer, Integer, Integer> multiply = (x, y) -> x * y;
        System.out.println(multiply.apply(3, 6));

        BiConsumer<Integer, Integer> printSum = (x, y) -> System.out.println(x + y);
        printSum.accept(45, 5);

    }
}
