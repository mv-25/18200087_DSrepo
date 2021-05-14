package projectCode20280;

/**Implements Stack ADT**/
public class LinkedStack<E> implements Stack<E> {

    private SinglyLinkedList<E> list = new SinglyLinkedList<>();   // an empty list

    public LinkedStack() { }                   // new stack relies on the initially empty list

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E e) {
        list.addFirst(e);

    }

    @Override
    public E top() {
        return list.first();
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    public String toString() {
        return list.toString();
    }

    public static void main(String[] args) {
        Stack<Integer> S = new LinkedStack<>();
        S.push(1);
        S.push(2);
        System.out.println(S.size());
        System.out.println(S.pop());
        System.out.println(S.pop());
        System.out.println(S.isEmpty());
    }

}
