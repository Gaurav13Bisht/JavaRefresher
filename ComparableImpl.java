import java.util.ArrayList;

class StudentWithComparable implements Comparable<StudentWithComparable>{

    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "StudentWithComparable{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public int compareTo(StudentWithComparable o) {
        return this.getName().length() - o.getName().length();
    }
}

public class ComparableImpl {

    public static void main(String[] args){
        StudentWithComparable studentWithComparable = new StudentWithComparable();
        studentWithComparable.setAge(24);
        studentWithComparable.setName("SauravJi");

        StudentWithComparable studentWithComparable2 = new StudentWithComparable();
        studentWithComparable2.setAge(25);
        studentWithComparable2.setName("Sisht");

        ArrayList<StudentWithComparable> studentWithComparableList = new ArrayList<>();
        studentWithComparableList.add(studentWithComparable);
        studentWithComparableList.add(studentWithComparable2);

        studentWithComparableList.sort(null);

        System.out.println(studentWithComparableList);
    }

}
