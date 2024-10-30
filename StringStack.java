public class StringStack {
    private int size;
    private String[] stack;
    private String top;

    StringStack() {
        this.size = 0;
        this.stack = new String[size];
    }

    boolean isEmpty() {
        return size == 0;
    }

    void push(String newElement) {
        String[] aux = new String[size + 1];
        if (size >= 0) System.arraycopy(stack, 0, aux, 0, size);
        aux[size] = newElement;
        size++;
        stack = aux;
        top = newElement;
    }

    String pop() {
        if (isEmpty()) return null;

        String[] aux = new String[size - 1];
        String element = stack[size - 1];
        System.arraycopy(stack, 0, aux, 0, size - 1);
        size--;
        stack = aux;
        if (size > 0) {
            top = stack[size - 1];
        } else top = null;
        return element;
    }

    String top() {
        if (isEmpty()) return null;
        else return top;
    }

    int size() { return size; }
}
