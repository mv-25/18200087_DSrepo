package projectCode20280;

/**Implements Stack ADT**/
public class ArrayStack<E> implements Stack<E> {

    public static final int CAPACITY=1000;   // default array capacity

    private E[] data;                        // generic array used for storage

    private int t = -1;                      // index of the top element in stack

     public ArrayStack() { this(CAPACITY); }  // constructs stack with default capacity

    @SuppressWarnings({"unchecked"})
    public ArrayStack(int capacity) {        // constructs stack with given capacity
        data = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return (t + 1);
    }

    @Override
    public boolean isEmpty() {
        return (t == -1);
    }

    @Override
    public void push(E e) throws IllegalStateException {
        if (size() == data.length) throw new IllegalStateException("Stack is full");
        data[++t] = e;                           // increment t before storing new item
    }

    @Override
    public E top() {
        if (isEmpty()) return null;
        return data[t];
    }

    @Override
    public E pop() {
        if (isEmpty()) return null;
        E answer = data[t];
        data[t] = null;
        t--;
        return answer;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int j = t; j >= 0; j--) {
            sb.append(data[j]);
            if (j > 0) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        Stack<Integer> S = new ArrayStack<>();
        S.push(1);
        S.push(2);
        System.out.println(S.size());
        System.out.println(S.pop());
        System.out.println(S.pop());
        System.out.println(S.isEmpty());

    }


}
