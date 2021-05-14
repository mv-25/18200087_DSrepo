package projectCode20280;

/**
 * Realization of a circular FIFO queue as an adaptation of a
 * CircularlyLinkedList. This provides one additional method not part of the
 * general Queue interface. A call to rotate() is a more efficient simulation of
 * the combination enqueue(dequeue()). All operations are performed in constant
 * time.

** Implements CircularQueue, Queue and List ADT**/
public class LinkedCircularQueue<E> implements CircularQueue<E> {

    private CircularlyLinkedList<E> circle = new CircularlyLinkedList<>();

    public LinkedCircularQueue() { }

    @Override
    public int size() {
        return circle.size();
    }

    @Override
    public boolean isEmpty() {
        return circle.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        circle.addLast(e);

    }

    @Override
    public E first() {
        return circle.first();
    }

    @Override
    public E dequeue() {
        return circle.removeFirst();
    }

    @Override
    public void rotate() {
        circle.rotate();
    }
    public String toString() {
        return circle.toString();
    }

    public static void main(String[] args) {
        LinkedCircularQueue arr = new LinkedCircularQueue();
        arr.enqueue(3);
        arr.enqueue(4);
        arr.enqueue(5);
        System.out.println(arr);
        arr.dequeue();
        System.out.println(arr);

    }
}
