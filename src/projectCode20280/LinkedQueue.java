package projectCode20280;

/** Implements Queue and List ADT**/
public class LinkedQueue<E> implements Queue<E> {

    private SinglyLinkedList<E> list = new SinglyLinkedList<>();   // an empty  list

    public LinkedQueue() { }                  // new queue relies on the initially empty list

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        list.addLast(e);

    }

    @Override
    public E first() {
        return list.first();
    }

    @Override
    public E dequeue() {
        return list.removeFirst();
    }

    public String toString() {
        return list.toString();
    }

    public static void main(String[] args) {
        LinkedQueue arr = new LinkedQueue();
        arr.enqueue(3);
        arr.enqueue(4);
        System.out.println(arr);
        arr.dequeue();
        System.out.println(arr);

    }

}
