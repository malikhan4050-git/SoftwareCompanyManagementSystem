import java.util.Scanner;

public class SoftwareCompanyManagement {
    static Scanner sc = new Scanner(System.in);
    static BST bst = new BST();
    static OperationStack undoStack = new OperationStack(100);
    static OperationStack redoStack = new OperationStack(100);
    static MaxHeap priorityHeap = new MaxHeap(100);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Software Company Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. Search Employee");
            System.out.println("3. Update/Delete Employee");
            System.out.println("4. Display All Employees");
            System.out.println("5. Generate Report");
            System.out.println("6. Process Urgent Tasks");
            System.out.println("7. Undo Last Operation");
            System.out.println("8. Redo Last Operation");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1: addEmployee(); break;
                case 2: searchEmployee(); break;
                case 3: updateDeleteEmployee(); break;
                case 4: bst.displayAll(); break;
                case 5: generateReport(); break;
                case 6: processUrgentTasks(); break;
                case 7: undo(); break;
                case 8: redo(); break;
                case 9: System.exit(0); break;
                default: System.out.println("Invalid choice!"); break;
            }
        }
    }

    static void addEmployee() {
        System.out.print("Enter ID: "); int id = sc.nextInt(); sc.nextLine();
        System.out.print("Enter Name: "); String name = sc.nextLine();
        System.out.print("Enter Designation: "); String desig = sc.nextLine();
        System.out.print("Enter Salary: "); double salary = sc.nextDouble();
        System.out.print("Enter Priority Level: "); int priority = sc.nextInt();
        Employee emp = new Employee(id, name, desig, salary, priority);
        bst.add(emp);
        undoStack.push(emp);
        priorityHeap.insert(emp);
        System.out.println("Employee added successfully!");
    }

    static void searchEmployee() {
        System.out.print("Enter ID to search: "); int id = sc.nextInt();
        Employee emp = bst.search(id);
        if (emp != null) emp.display();
        else System.out.println("Employee not found!");
    }

    static void updateDeleteEmployee() {
        System.out.print("Enter ID to update/delete: "); int id = sc.nextInt();
        Employee emp = bst.search(id);
        if (emp == null) { System.out.println("Employee not found!"); return; }
        System.out.println("1. Update\n2. Delete\nChoose: "); int choice = sc.nextInt();
        sc.nextLine();
        if (choice == 1) {
            System.out.print("Enter new Name: "); emp.name = sc.nextLine();
            System.out.print("Enter new Designation: "); emp.designation = sc.nextLine();
            System.out.print("Enter new Salary: "); emp.salary = sc.nextDouble();
            System.out.print("Enter new Priority Level: "); emp.priorityLevel = sc.nextInt();
            System.out.println("Employee updated successfully!");
        } else if (choice == 2) {
            bst.delete(id);
            undoStack.push(emp);
            System.out.println("Employee deleted successfully!");
        } else {
            System.out.println("Invalid choice!");
        }
    }

    static void generateReport() {
        System.out.println("Report");
        System.out.println("Total Employees: ");
        System.out.println(countNodes(bst.root));
    }

    static int countNodes(BST.BSTNode node) {
        if (node == null) return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    static void processUrgentTasks() {
        System.out.println("Processing Urgent Tasks");
        Employee emp = priorityHeap.extractMax();
        if (emp != null) {
            System.out.println("Processing Employee with highest priority:");
            emp.display();
        } else {
            System.out.println("No urgent tasks in the queue.");
        }
    }

    static void undo() {
        if (!undoStack.isEmpty()) {
            Employee emp = undoStack.pop();
            redoStack.push(emp);
            bst.delete(emp.id);
            System.out.println("Undo performed successfully.");
        } else System.out.println("Nothing to undo.");
    }

    static void redo() {
        if (!redoStack.isEmpty()) {
            Employee emp = redoStack.pop();
            bst.add(emp);
            undoStack.push(emp);
            System.out.println("Redo performed successfully.");
        } else System.out.println("Nothing to redo.");
    }
}
