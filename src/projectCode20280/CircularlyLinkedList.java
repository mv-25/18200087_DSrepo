package projectCode20280;

import java.util.Iterator;

/**Implements List ADT
 * Similar to SLL except tail point to the head**/
public class CircularlyLinkedList<E> implements List<E> {

	private class Node<E> {
		private E element;     // an element stored at this node

		private Node<E> next;  // a reference to the subsequent node in the list

		public Node(E e, Node<E> n) {
			element = e;
			next = n;
		}

		// Accessor methods
		public E getElement() { return element; }

		public Node<E> getNext() { return next; }

		// Modifier methods
		public void setNext(Node<E> n) { next = n; }
	}

	//nested ListIterator() class
	private class ListIterator implements Iterator<E> {
		Node<E> curr;
		boolean flag;
		public ListIterator() {
			if(!isEmpty()){
				curr=tail.next;
				flag=true;
			}
		}
		public boolean hasNext() {
			return !(isEmpty()|| curr==tail.next && !flag);
		}
		@Override
		public E next() {
			E res = curr.getElement();
			flag=false;
			curr = curr.getNext();
			return res;
		}
	}

	// instance variables of the CircularlyLinkedList
	private Node<E> tail = null;                  // we store tail (but not head)

	private int size = 0;                         // number of nodes in the list

	public CircularlyLinkedList() { }             // constructs an initially empty list

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	public E first() {             // returns (but does not remove) the first element
		if (isEmpty()) return null;
		return tail.getNext().getElement();         // the head is *after* the tail
	}

	public E last() {              // returns (but does not remove) the last element
		if (isEmpty()) return null;
		return tail.getElement();
	}

	@Override
	public E get(int i) {
		if (i < 0 || i >= size) {
			throw new IndexOutOfBoundsException("Index is Invalid");
		}

		// If index=0 , return head
		if (i == 0) {
			return tail.next.element;
		}
		// If index= size, return last node
		if (i == this.size - 1) {
			return tail.element;
		}
		Node<E> temp = tail.next;
		for (int n = 0; n<i; n++) {
				temp = temp.next;
		}
		return temp.element;
	}

	@Override
	public void add(int i, E e) {
		if (i < 0 || i > size) {
			throw new IllegalArgumentException("Index is Invalid");
		}

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

		if(i<size)
		{
			Node<E> newNode = new Node<E>(e, null);
			Node<E> tempNode = tail;
			for (int n = 0; tempNode.next != tail;++n) {
				if (i == n) {
					newNode.next = tempNode.next;
					tempNode.next = newNode;
				} else
					tempNode = tempNode.next;
			}
			size++;
		}
	}

	@Override
	public E remove(int i) {
		if (i < 0 || i >= size) {
			throw new IllegalArgumentException("Index is Invalid");
		}

		Node<E> current = tail.next, previous = tail.next;
		for (int n = 0; n < i; n++) {
			if (current.next == tail) {
				break;
			}
			previous = current;
			current = current.next;
		}
		if (i == 0) {
			removeFirst();
		}
		else {
			previous.next = current.next;
		}
		size--;
		return null;
	}

	@Override
	public E removeFirst() {
		if (isEmpty()) return null;              // nothing to remove
		Node<E> head = tail.getNext();
		if (head == tail) tail = null;           // must be the only node left
		else tail.setNext(head.getNext());       // removes "head" from the list
		size--;
		return head.getElement();
	}

	@Override
	public E removeLast() {
		Node<E> temp = tail;
		if (isEmpty()) return null;
		else
		{
			tail=tail.next;
			size--;
		}
		return temp.element;
	}

	@Override
	public Iterator<E> iterator() {
		return new ListIterator();
	}

	@Override
	public void addFirst(E e) {
		if (size == 0) {
			tail = new Node<>(e, null);
			tail.setNext(tail);                     // link to itself circularly
		} else {
			Node<E> newest = new Node<>(e, tail.getNext());
			tail.setNext(newest);
		}
		size++;
	}

	@Override
	public void addLast(E e) {
		addFirst(e);             // insert new element at front of list
		tail = tail.getNext();   // now new element becomes the tail

	}

	public void rotate() {  			// rotate the first element to the back of the list
		if (tail != null)                // if empty, do nothing
			tail = tail.getNext();         // the old head becomes the new tail

	}

	@Override
	public String toString() {
		if (tail == null) return "()";
		StringBuilder sb = new StringBuilder("[");
		Node<E> walk = tail;
		do {
			walk = walk.getNext();
			sb.append(walk.getElement());
			if (walk != tail)
				sb.append(", ");
		} while (walk != tail);
		sb.append("]");
		return sb.toString();
	}
	
	public static void main(String[] args) {
		CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
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
