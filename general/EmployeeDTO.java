package general;

public record EmployeeDTO(int empId, String name) {
}



class Employee{
    public static void main(String[] args) {
        EmployeeDTO employeeDTO = new EmployeeDTO(1, "Gaurav");

        // It provided getters, toString, hashCode, equals and Constructor by default
        employeeDTO.empId();
        employeeDTO.name();

        // Not allowed since immutable
//        employeeDTO.setEmpId(2);
//        employeeDTO.setName("Gaurav2");

        employeeDTO.toString();
        employeeDTO.hashCode();
        employeeDTO.equals(employeeDTO);
    }
}