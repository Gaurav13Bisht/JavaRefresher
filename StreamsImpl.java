import java.util.*;
import java.util.stream.Collectors;

public class StreamsImpl {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 1, 2));
        System.out.println(list);

        intermediateOperations(list);
//        terminalOperations(list);
    }

    private static void intermediateOperations(List<Integer> list) {
        // FILTER even numbers
        List<Integer> updatedList = list.stream().filter(x -> x % 2 == 0).toList();
        System.out.println(updatedList);

        // MAP to Student list
        List<Student> studentList = list.stream().map(Student::new).toList();
        System.out.println(studentList);

        // SORTED
        List<Integer> ascendingList = list.stream().sorted().toList();
        // List<Integer> ascendingList = list.stream().sorted(Comparator.comparing(put some logic).thenComparing(put some logic)).toList();
        System.out.println(ascendingList);

        List<Integer> descendingList = list.stream().sorted(Collections.reverseOrder()).toList();
        System.out.println(descendingList);

        // DISTINCT
        List<Integer> distinctList = list.stream().distinct().toList();
        System.out.println(distinctList);

        // LIMIT
        List<Integer> limitedList = list.stream().limit(4).toList();
        System.out.println(limitedList);

        // SKIP
        List<Integer> skippedList = list.stream().skip(2).toList();
        System.out.println(skippedList);

        // FLAT MAP
        List<List<String>> list2 = new ArrayList<>();
        list2.add(new ArrayList<>());
        list2.get(0).add("Gaurav");
        list2.get(0).add("Bisht");

        list2.add(new ArrayList<>());
        list2.get(1).add("Age");
        list2.get(1).add("24");

        // This looks like this:
//        [{
//            "Gaurav", "Bisht"
//        },
//        {
//            "Age", "24"
//        }]

        // Now flatmap will make this nested list into a single level flat list
        System.out.println(list2.stream().flatMap(x-> x.stream()).toList());

    }

    private static void terminalOperations(List<Integer> list) {

        // COLLECT
        List<Integer> squaredList = list.stream().map(x -> x * x).collect(Collectors.toList());
        // List<Integer> squaredList = list.stream().map(x -> x * x).toList();    // Both same, this came in newer versions

        // FOR EACH
        list.stream().forEach(System.out::println);

        // REDUCE: This reduces the whole collection to a single entity
        //         We need to give logic which will definitely resolves to a single value after operating with all elements
        Optional<Integer> sum = list.stream().reduce((x, y) -> x + y);
        System.out.println(sum.get());

        // COUNT:
        System.out.println(list.stream().filter(x -> x % 2 == 0).count());

        // AnyMatch, AllMatch and NoneMatch
        System.out.println(list.stream().anyMatch(x -> x % 2 == 0));    // This will give true if it gets any even number
        System.out.println(list.stream().allMatch(x -> x > 0));    // This will give true if all numbers are > 0
        System.out.println(list.stream().noneMatch(x -> x > 30));   // This will give true if all numbers are <= 30

        // findFirst, findAny
        System.out.println(list.stream().filter(x -> x % 2 == 0).findFirst().get()); // This will give the first element of the stream according to the order of the collection
        System.out.println(list.stream().filter(x -> x % 2 == 0).findAny().get());  // This will give any element of the stream

    }

}