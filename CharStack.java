public class CharStack {
    private int size;
    private char[] stack;
    private char top;

    public CharStack() {
        this.size = 0;
        this.stack = new char[size];
    }

    boolean isEmpty() {
        return size == 0;
    }

    void push(char newElement) {
        char[] aux = new char[size + 1];
        if (size >= 0) System.arraycopy(stack, 0, aux, 0, size);
        aux[size] = newElement;
        size++;
        stack = aux;
        top = newElement;
    }

    char pop() {
        if (isEmpty()) return 0;

        char[] aux = new char[size - 1];
        char element = stack[size - 1];
        System.arraycopy(stack, 0, aux, 0, size - 1);
        size--;
        stack = aux;
        if (size > 0) {
            top = stack[size - 1];
        } else top = 0;
        return element;
    }

    char top() {
        if (isEmpty()) return 0;
        else return top;
    }
}
