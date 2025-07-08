import java.util.ArrayList;

public class EmployeeCRUD {
    private ArrayList<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee emp) {
        employees.add(emp);
    }

    public Employee getEmployeeById(int id) {
        for (Employee e : employees) {
            if (e.getId() == id) return e;
        }
        return null;
    }

    public boolean updateEmployee(int id, String name, String department) {
        Employee emp = getEmployeeById(id);
        if (emp != null) {
            emp.setName(name);
            emp.setDepartment(department);
            return true;
        }
        return false;
    }

    public boolean deleteEmployee(int id) {
        return employees.removeIf(e -> e.getId() == id);
    }

    public void displayAll() {
        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        EmployeeCRUD crud = new EmployeeCRUD();

        crud.addEmployee(new Employee(1, "Yash", "SWE")); // Create
        crud.addEmployee(new Employee(2, "Sanyam", "Data")); //Create

        System.out.println("Get employee with ID 1: " + crud.getEmployeeById(1)); //Read

        crud.updateEmployee(2, "Pranavi", "Data"); //Update
        System.out.println("After update:");
        crud.displayAll();

        crud.deleteEmployee(2); //Delete
        System.out.println("After deletion:");
        crud.displayAll();
    }
}
