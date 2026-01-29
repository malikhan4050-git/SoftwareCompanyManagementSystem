
public class BST {
    class BSTNode {
        Employee emp;
        BSTNode left, right;

        BSTNode(Employee emp) {
            this.emp = emp;
            left = right = null;
        }
    }

    BSTNode root;

    public BST() {
        root = null;
    }

    public void add(Employee emp) {
        root = addRec(root, emp);
    }

    private BSTNode addRec(BSTNode node, Employee emp) {
        if (node == null) return new BSTNode(emp);
        if (emp.id < node.emp.id) node.left = addRec(node.left, emp);
        else if (emp.id > node.emp.id) node.right = addRec(node.right, emp);
        else System.out.println("Employee ID already exists!");
        return node;
    }

    public Employee search(int id) {
        return searchRec(root, id);
    }

    private Employee searchRec(BSTNode node, int id) {
        if (node == null) return null;
        if (id == node.emp.id) return node.emp;
        if (id < node.emp.id) return searchRec(node.left, id);
        return searchRec(node.right, id);
    }

    public void displayAll() {
        inOrder(root);
    }

    private void inOrder(BSTNode node) {
        if (node != null) {
            inOrder(node.left);
            node.emp.display();
            inOrder(node.right);
        }
    }

    public void delete(int id) {
        root = deleteRec(root, id);
    }

    private BSTNode deleteRec(BSTNode node, int id) {
        if (node == null) return null;
        if (id < node.emp.id) node.left = deleteRec(node.left, id);
        else if (id > node.emp.id) node.right = deleteRec(node.right, id);
        else {
            if (node.left == null) return node.right;
            else if (node.right == null) return node.left;
            node.emp = minValue(node.right);
            node.right = deleteRec(node.right, node.emp.id);
        }
        return node;
    }

    private Employee minValue(BSTNode node) {
        Employee minv = node.emp;
        while (node.left != null) {
            node = node.left;
            minv = node.emp;
        }
        return minv;
    }
}
