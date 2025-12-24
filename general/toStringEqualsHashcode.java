package general;

public class toStringEqualsHashcode {

    public static void main(String... args) {
        Student student = new Student();

        student.setAge(24);
        student.setName("Gaurav");

        // toString() check
        System.out.println(student);

        Student student2 = new Student();
        student2.setAge(24);
        student2.setName("Gaurav");

        System.out.println(student.equals(student2));


    }
}