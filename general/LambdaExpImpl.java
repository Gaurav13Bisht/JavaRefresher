package general;

interface AddNumbers{
    public int calculate(int a, int b);
}

public class LambdaExpImpl {
    public static void main(String[] args) {
        // Lambda expression is used to write anonymous functions
        // It doesn't have any access modifier, return type or name

        // 1. Using Anonymous class for anonymous function
        AddNumbers obj = new AddNumbers() {
            @Override
            public int calculate(int a, int b) {
                return a+ b;
            }
        };

        System.out.println(obj.calculate(1, 2));

        // 2. Using Lambda expression for anonymous function

//        A valid function definition for calculate would be:
//        public int calculate(int a, int b){
//            return a + b;
//        }
//        To convert it to Lambda exp, just remove access modifier, return type and name of the function and add -> sign:
//        (int a, int b) -> {
//            return a + b;
//        }
//        This will be your lambda expression

        AddNumbers obj2 = (int a, int b) -> {
                                return a + b;
                            };

        // More shorter versions:
//        general.AddNumbers obj2 = (a, b) -> a + b;
//        general.AddNumbers obj2 = Integer::sum;   // This is called Method reference

        System.out.println(obj2.calculate(1, 2));
    }
}
