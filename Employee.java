
public class Employee {
    int id;
    String name;
    String designation;
    double salary;
    int priorityLevel; 


    public Employee(int id, String name, String designation, double salary, int priorityLevel) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
        this.priorityLevel = priorityLevel;
    }

    
    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Designation: " + designation +
                ", Salary: " + salary + ", Priority: " + priorityLevel);
    }
}
