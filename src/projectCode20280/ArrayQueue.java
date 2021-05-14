package projectCode20280;

/** Implements Queue ADT**/
public class ArrayQueue<E> implements Queue<E> {

    public static final int CAPACITY = 1000;      // default array capacity

    private E[] data;                             // generic array used for storage

    private int f = 0;                            // index of the front element

    private int s = 0;                           // current number of elements

    // constructors
    public ArrayQueue() {this(CAPACITY);}         // constructs queue with default capacity


    public ArrayQueue(int capacity) {             // constructs queue with given capacity
        data = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return s;
    }

    @Override
    public boolean isEmpty() {
        return (s == 0);
    }

    @Override
    public void enqueue(E e) throws IllegalStateException {
        if (s == data.length) throw new IllegalStateException("Queue is full");
        int avail = (f + s) % data.length;
        data[avail] = e;
        s++;
    }

    @Override
    public E first() {
        if (isEmpty()) return null;
        return data[f];
    }

    @Override
    public E dequeue() {
        if (isEmpty()) return null;
        E answer = data[f];
        data[f] = null;
        f = (f + 1) % data.length;
        s--;
        return answer;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        int k = f;
        for (int j=0; j < s; j++) {
            if (j > 0)
                sb.append(", ");
            sb.append(data[k]);
            k = (k + 1) % data.length;
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        ArrayQueue arr = new ArrayQueue();
        arr.enqueue(3);
        arr.enqueue(4);
        System.out.println(arr);
        arr.dequeue();
        System.out.println(arr);

    }


}
