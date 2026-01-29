
public class OperationStack {
    private Employee[] stack;
    private int top;
    private int maxSize;

    public OperationStack(int size) {
        maxSize = size;
        stack = new Employee[size];
        top = -1;
    }

    public void push(Employee emp) {
        if (top < maxSize - 1) stack[++top] = emp;
        else System.out.println("Stack overflow!");
    }

    public Employee pop() {
        if (top >= 0) return stack[top--];
        else return null;
    }

    public boolean isEmpty() {
        return top == -1;
    }
}
