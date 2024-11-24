import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParallelStreamImpl {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 1, 2));

       list.stream().forEach(System.out::println);
       list.parallelStream().forEach(System.out::println);
    }
}
