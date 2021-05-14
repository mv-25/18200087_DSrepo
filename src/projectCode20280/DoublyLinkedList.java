package projectCode20280;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**Implements List ADT
 * Similar to SLL except each node in a doubly linked list has
 * references to both the node that follows it and the node that precedes it.**/
public class DoublyLinkedList<E> implements List<E>, Iterable<E> {

    private class Node<E> {
        private E element;

        private Node<E> prev;            // reference to the previous node in the list

        private Node<E> next;            // reference to the subsequent node in the list
        /**
         * Creates a node with the given element and next node.
         *
         * @param e  the element to be stored
         * @param p  reference to a node that should precede the new node
         * @param n  reference to a node that should follow the new node
         */
        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }

        public E getElement() { return element; }
        public void setElement(E element) {
            this.element = element;
        }
        public Node<E> getPrev() { return prev; }
        public Node<E> getNext() { return next; }
        public void setPrev(Node<E> p) { prev = p; }
        public void setNext(Node<E> n) { next = n; }
    } //end of Node class

    //nested ListIterator() class
    private class ListIterator implements Iterator<E> {
        private Node cur      = header.next;  // the node that is returned by next()
        private Node lastAccessed = null;      // the last node to be returned by prev() or next()
        // reset to null upon intervening remove() or add()
        private int count = 0;

        public boolean hasNext()      { return count < size; }
        public boolean hasPrevious()  { return count > 0; }
        public int previousIndex()    { return count - 1; }
        public int nextIndex()        { return count;     }

        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            lastAccessed = cur;
            E e = (E) cur.element;
            cur = cur.next;
            count++;
            return e;
        }
    }

    // instance variables of the DoublyLinkedList
    private Node<E> header;

    private Node<E> trailer;

    private int size = 0;

    public E first() {
        if (isEmpty()) return null;
        return header.getNext().getElement();   // first element is beyond header
    }

    public E last() {
        if (isEmpty()) return null;
        return trailer.getPrev().getElement();    // last element is before trailer
    }

    public  DoublyLinkedList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);   // trailer is preceded by header
        header.setNext(trailer);                          // header is followed by trailer
    }


    private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
        // create and link a new node
        Node<E> newest = new Node<>(e, predecessor, successor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    //returns element at a given index
    public E get(int i) {
        if (i < 0 || i > this.size - 1) {
            throw new IndexOutOfBoundsException("Index not available.");
        }
        // If index=0 , return head
        if (i == 0) {
            return this.header.next.element;
        }
        // If index= size, return last node
        if (i == this.size - 1) {
            return this.trailer.prev.element;
        }
        int pointer = 0;
        DoublyLinkedList.Node cur = header.next;
        while (pointer <= i) {
            if (pointer == i) {
                return (E) cur.getElement();		//specified element is returned
            } else {
                cur = cur.next;
                pointer++;
            }
        }
        return null;
    }

    @Override
    //inserts element at a given index
    public void add(int i, E e) throws IndexOutOfBoundsException {
        if (e == null) {
            return;
        }
        // If index=0, add the data at head
        if (i == 0) {
            addFirst(e);
            return;
        }
        // If index= size, add the data at last
        if (i == this.size) {
            addLast(e);
        }
        else if (i < this.size)
        {
            int count = 0;
            Node cur = header.next;
            Node newNode = new Node(e, null,null);

            while (cur.next != null )
            {
                ++count;
                if(count==i)
                {
                    Node temp = cur.prev;       //new node is added at the index
                    temp.next=newNode;
                    newNode.next = cur;
                    newNode.prev = temp;
                    cur.prev = newNode;

                }
                else{
                    cur=cur.next;
                }
            }
            size++;
        } else {
            throw new IndexOutOfBoundsException("Index not available.");
        }

    }

    //remove a given node
    private E remove(Node<E> node) {
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        return node.getElement();
    }

    @Override
    public Iterator<E> iterator() {
      return new ListIterator();
    }

    @Override
    public E removeFirst() {
        if (isEmpty())
            return null; // nothing to remove
        return remove(header.getNext()); // first element is beyond header
    }

    @Override
    public E removeLast() {
        if (isEmpty())
            return null;                  // nothing to remove
        return remove(trailer.getPrev());            // last element is before trailer
    }


    @Override
    public void addFirst(E e) {
        addBetween(e, header, header.getNext()); // place just after the header

    }

    @Override
    public void addLast(E e) {
        addBetween(e, trailer.getPrev(), trailer); // place just before the trailer
    }

    @Override
    //remove an  element at a given index
    public E remove(int i) {
        if (header.next == null) {
            throw new RuntimeException("Cannot delete, list empty.");
        }
        if(i ==0)
            removeFirst();
        else if(i==size)
            removeLast();
        else{
            int count=0;
            Node cur = header.next;
            for (int k=0; cur!=null && k<i-1; k++)		//finding the previous element
            {
                cur.prev = cur;
                cur = cur.next;
            }
            Node next = cur.next.next;                  // unlinking from both directions
            next.prev = cur.next.prev;
            cur.next.prev.next = next;
            size--;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> walk = header.getNext();
        while (walk != trailer) {
            sb.append(walk.getElement());
            walk = walk.getNext();
            if (walk != trailer)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> ll = new DoublyLinkedList<>();
        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(3);
        ll.addFirst(4);
        System.out.println(ll.get(3));
        ll.addFirst(5);
        ll.add(3, 2);
        System.out.println(ll);
        ll.addFirst(100);
        ll.addLast(-100);
        System.out.println(ll);
        ll.removeFirst();
        System.out.println(ll);
        ll.removeLast();
        System.out.println(ll);
       ll.remove(2);
        System.out.println(ll);
        ll.removeFirst();
        System.out.println(ll);
        ll.removeLast();
        System.out.println(ll);
        ll.removeFirst();
        System.out.println(ll);
        ll.addFirst(9999);
        ll.addFirst(8888);
        ll.addFirst(7777);
        System.out.println(ll);
        System.out.println(ll.get(0));
        System.out.println(ll.get(1));
        System.out.println(ll.get(2));
        System.out.println(ll);
    }


}
