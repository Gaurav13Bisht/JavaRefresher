package general;

import java.util.*;

class StudentComparator implements Comparator<Student> {
    @Override
    public int compare(Student p, Student q){
        return p.getAge() - q.getAge();     // Ascending order
    }
}

public class ComparatorImpl {
    public static void main(String[] args){
        Student student = new Student();
        student.setAge(24);
        student.setName("Gaurav");

        Student student2 = new Student();
        student2.setAge(25);
        student2.setName("Bisht");

        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(student2);

        studentList.sort(new StudentComparator());
//        Collections.sort(studentList, new general.StudentComparator());

        System.out.println(studentList);
    }
}