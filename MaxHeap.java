
public class MaxHeap {
    Employee[] heap;
    int size;

    public MaxHeap(int capacity) {
        heap = new Employee[capacity];
        size = 0;
    }

    public void insert(Employee emp) {
        heap[size] = emp;
        int i = size;
        size++;
        while (i != 0 && heap[parent(i)].priorityLevel < heap[i].priorityLevel) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    public Employee extractMax() {
        if (size <= 0) return null;
        Employee root = heap[0];
        heap[0] = heap[size - 1];
        size--;
        maxHeapify(0);
        return root;
    }

    private void maxHeapify(int i) {
        int l = left(i);
        int r = right(i);
        int largest = i;
        if (l < size && heap[l].priorityLevel > heap[largest].priorityLevel) largest = l;
        if (r < size && heap[r].priorityLevel > heap[largest].priorityLevel) largest = r;
        if (largest != i) {
            swap(i, largest);
            maxHeapify(largest);
        }
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int left(int i) { return 2 * i + 1; }
    private int right(int i) { return 2 * i + 2; }

    private void swap(int i, int j) {
        Employee temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}
