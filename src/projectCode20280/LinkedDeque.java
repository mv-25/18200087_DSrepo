package projectCode20280;

public class LinkedDeque<E> implements Deque<E> {
    private Node<E> front;
    private Node<E> rear;

    @Override
    public int size() {
        int count = 0;
        for (Node<E> node = front; node != null; node = node.getNext())
            count++;
        return count;
    }

    @Override
    public boolean isEmpty() {
        if(front == null){
            return true;
        }
        return false;
    }

    @Override
    public E first() {
        if(front == null){
            return null;
        }
        return front.getValue();
    }

    @Override
    public E last() {
        if(rear == null){
            return null;
        }
        return rear.getValue();
    }

    @Override
    public void addFirst(E e) {
        Node<E> nd = new Node<E>();
        nd.setValue(e);
        nd.setNext(front);
        if(front != null) front.setPrev(nd);
        if(front == null) rear = nd;
        front = nd;

    }

    @Override
    public void addLast(E item) {
        Node<E> nd = new Node<E>();
        nd.setValue(item);
        nd.setPrev(rear);
        if(rear != null) rear.setNext(nd);
        if(rear == null) front = nd;

        rear = nd;

    }

    @Override
    public E removeFirst() {
        if(front == null){
            return null;
        }
        Node<E> tmpFront = front.getNext();
        if(tmpFront != null) tmpFront.setPrev(null);
        if(tmpFront == null) rear = null;
        E returnVal = front.getValue();
        front = tmpFront;
        return returnVal;
    }

    @Override
    public E removeLast() {
        if(rear == null){

            return null;
        }
        Node<E> tmpRear = rear.getPrev();
        if(tmpRear != null) tmpRear.setNext(null);
        if(tmpRear == null) front = null;
        E e = rear.getValue();
        rear = tmpRear;
        return e;
    }

    public String toString()
    {
        if (front == null)
        {
            return "[]";
        }
        else
        {
            String result = "[" + front.getValue();
            Node current = front.getNext();
            while (current != null)
            {
                result += ", " + current.getValue() ;
                current = current.getNext();
            }
            result += "]";
            return result;
        }
    }

}

class Node<E>{
    private Node<E> prev;
    private Node<E> next;
    private E value;

    public Node<E> getPrev() {
        return prev;
    }
    public void setPrev(Node<E> prev) {
        this.prev = prev;
    }
    public Node<E> getNext() {
        return next;
    }
    public void setNext(Node<E> next) {
        this.next = next;
    }
    public E getValue() {
        return value;
    }
    public void setValue(E value) {
        this.value = value;
    }
}
