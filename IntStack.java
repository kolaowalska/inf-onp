public class IntStack {
    private int size;
    private int[] stack;
    private int top;

    IntStack() {
        this.size = 0;
        this.stack = new int[size];
    }

    boolean isEmpty() {
        return size == 0;
    }

    void push(int element) {
        int[] aux = new int[size + 1];
        if (size >= 0) System.arraycopy(stack, 0, aux, 0, size);
        aux[size] = element;
        size++;
        stack = aux;
        top = element;
    }

    int pop() {
        if (isEmpty()) return 0;

        int[] aux = new int[size - 1];
        int element = stack[size - 1];
        System.arraycopy(stack, 0, aux, 0, size - 1);
        size--;
        stack = aux;
        if (size > 0) {
            top = stack[size - 1];
        } else top = 0;
        return element;
    }
}
