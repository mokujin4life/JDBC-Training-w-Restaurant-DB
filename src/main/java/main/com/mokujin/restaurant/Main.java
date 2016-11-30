package main.com.mokujin.restaurant;


public class Main {
    public static void main(String[] args) {
        EmployeeDao employeeDao = new EmployeeDao();

        System.out.println("All employees: ");
        employeeDao.getAll().forEach(System.out::println);

        System.out.println("Employee with id 2: ");
        System.out.println(employeeDao.load(2));
    }
}
