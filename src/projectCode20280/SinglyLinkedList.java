package projectCode20280;

import java.util.Iterator;
import java.lang.*;

/**Implements List ADT
 * A Singly Linked List is a collection of nodes that together form a linear
 * ordering and the tail is a node having a null next reference,
 * which indicates the end of the list.**/
public class SinglyLinkedList<E> implements List<E>, Iterable<E> {

	private class Node<E> {
		private E element; // reference to the element stored at this node
		private Node<E> next; // reference to the subsequent node in the list
		/**
		 * Creates a node with the given element and next node.
		 *
		 * @param e the element to be stored
		 * @param n reference to a node that should follow the new node
		 */
		public Node(E e, Node<E> n) {
			element = e;
			next = n;
		}
		public E getElement() { return element; }
		public Node<E> getNext() { return next; }
		public void setNext(Node<E> n) { next = n; }
	}

 	//nested ListIterator() class
	private class ListIterator implements Iterator<E> {
		Node curr;
		public ListIterator() {
			curr = head;
		}
		public boolean hasNext() {
			return curr != null;
		}
		@Override
		public E next() {
			E res = (E) curr.getElement();
			curr = curr.getNext();
			return res;
		}
	}

	// instance variables of the SinglyLinkedList
	private Node<E> head = null;               // head node of the list (or null if empty)

	private Node<E> tail = null;               // last node of the list (or null if empty)

	private int size = 0;                      // number of nodes in the list

	public SinglyLinkedList() { }              // constructs an initially empty list

	public E first() {             			   // returns (but does not remove) the first element
		if (isEmpty()) return null;
		return head.getElement();
	}

	//returns last element of the list
	public E last()
	{
		if (isEmpty()) return null;
		return tail.getElement();
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	//returns element at a given index
	public E get(int i) {
		if (i < 0 || i > this.size - 1) {
			throw new IndexOutOfBoundsException("Index not available.");
		}
		// If index=0 , return head
		if (i == 0) {
			return head.getElement();
		}
		// If index= size, return last node
		if (i == this.size - 1) {
			return tail.getElement();
		}
		//find the given index
		int pointer = 0;
		Node<E> cur = head;
		while (pointer <= i) {
			if (pointer == i) {
				return cur.getElement();		//specified element is returned
			} else {
				 cur = cur.next;
				pointer++;
			}
		}
		return null;
	}

	@Override
	//insert node at given index
	public void add(int i, E e) throws IndexOutOfBoundsException {
		if (e == null) {
			return;
		}
		// If index=0 , add the data at head
		if (i == 0) {
			addFirst(e);
			return;
		}
		// If index= size, add the data at last
		if (i == this.size) {
			addLast(e);
		}
		//finding index
		if(i<size)
		{
			int count = 0;
			Node cur;
			cur=head;
			Node newNode = new Node<E>(e, null);
			while (cur != null )
			{
				++count;
				if(count==i){
				newNode.next = cur.next;		//node is inserted
				cur.next = newNode;}
				else{
					cur=cur.next;
				}
			}
			size++;
		}
		else
		{
			throw new IndexOutOfBoundsException("Index not available.");
		}

	}

	@Override
	//remove element at given index
	public E remove(int i) {
		if (head == null) {
			throw new RuntimeException("Cannot delete, list empty.");
		}
		if(i ==0)
			removeFirst();
		else if(i==size)
			removeLast();
		else{
			int count=0;
			Node cur = head;
			for (int k=0; cur!=null && k<i-1; k++)		//finding the previous element
				cur = cur.next;
			Node next = cur.next.next;
			cur.next = next;							//unlinking the node
		}
		return null;
	}

	@Override
	public Iterator<E> iterator() {
		return new ListIterator();
	}

	@Override
	public int size() {
		return size;
	}


	@Override
	public E removeFirst() {
		if (isEmpty())
			return null;              // nothing to remove
		E element = head.getElement();
		head = head.getNext();                   // will become null if list had only one node
		size--;
		if (size == 0)
			tail = null;                           // special case as list is now empty
		return element;
	}

	@Override
	//remove last element of the list
	public E removeLast() {
		Node cur =head;
		E element = tail.element;
		if(isEmpty())
			return null;
		else if(size==1)		//only one element in the list
		{
			head = null;
			tail = null;
		}
		else
		{
			while(cur.next !=tail)
				cur = cur.next;
		}
		tail = cur;			//finding the tail and unlinking it
		tail.next =null;
		return element;
	}

	@Override
	/**
	 * Adds an element to the front of the list.
	 *
	 * @param e the new element to add
	 */
	public void addFirst(E e) {
		head = new Node<>(e, head);              // create and link a new node
		if (size == 0)
			tail = head;                           // special case: new node becomes tail also
		size++;
	}

	@Override
	/**
	 * Adds an element to the end of the list.
	 *
	 * @param e the new element to add
	 */
	public void addLast(E e) {
		Node<E> newest = new Node<>(e, null);    // node will eventually be the tail
		if (isEmpty())
			head = newest;                         // special case: previously empty list
		else
			tail.setNext(newest);                  // new node after existing tail
		tail = newest;                           // new node becomes the tail
		size++;
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder("[");
		if(isEmpty())
			return "Empty String";
		else {

			Node<E> walk = head;
			while (walk != null) {
				sb.append(walk.getElement());
				if (walk != tail)
					sb.append(", ");
				walk = walk.getNext();
			}
			sb.append("]");
			return sb.toString();
		}
	}


	public static void main(String[] args) {
		SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
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
