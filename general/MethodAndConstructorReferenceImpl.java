package general;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MethodAndConstructorReferenceImpl {

    public Integer count;

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4));

        list.forEach(x -> print(x));
        list.forEach(MethodAndConstructorReferenceImpl::print); // Method reference
        // If lets suppose the method wasn't static, then we can create a object of the class and put (objectName::print)

        List<MethodAndConstructorReferenceImpl> listClass =
                list.stream().map(x -> new MethodAndConstructorReferenceImpl(x)).toList();

        List<MethodAndConstructorReferenceImpl> listClass2 =
                list.stream().map(MethodAndConstructorReferenceImpl::new).toList();  // Constructor reference

        System.out.println(listClass2);
    }

    public static void print(Integer s){
        System.out.println(s);
    }

    public MethodAndConstructorReferenceImpl(Integer count){
        this.count = count;
    }

    @Override
    public String toString() {
        return "general.MethodAndConstructorReferenceImpl{" +
                "count=" + count +
                '}';
    }
}
