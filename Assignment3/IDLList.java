/**
 * 
 */
package Assignments;

import java.util.ArrayList;

//UML diagram
/*****************************************************
 *                    Node[E]                         *
 *----------------------------------------------------*
 * E Data                                             *
 * Node{E] next                                       *
 * Node[E] prev	                                     *
 * -------------------------------------------------- *
 * Node (E elem)                                      *
 * Node (E elem, Node[E] prev, Node[E] next)          *
 * ---------------------------------------------------*
 *                  IDLList[E]                        *
 * ---------------------------------------------------*
 * private Node[E] head                               *
 * private Node[E] tail                               *
 * private int size                                   * 
 * private ArrayList[Node[E]] indices                 *
 * ---------------------------------------------------*
 * public IDLList ()                                  *
 * public boolean add (int index, E elem)             *
 * public boolean add (E elem)                        *
 * public boolean append (E elem)                     *
 * public E get (int index)                           *
 * public E getHead ()                                *
 * public E getLast ()                                *
 * public int size()                                  *
 * public E remove ()                                 *
 * public E removeLast ()                             *
 * public E removeAt (int index)                      *
 * public boolean remove (E elem)                     *
 * public String toString()                           *
 *****************************************************/
/**
 * @author Sangram Thorat CWID : 20007345 This assignment implements a
 *         double-linked list with fast accessing.
 * 
 */
public class IDLList<E> {

	// private inner class
	private class Node<E> {
		E data;
		Node<E> prev;
		Node<E> next;

		// constructor
		public Node(E elem) {
			data = elem;
			prev = null;
			next = null;
		}

		// other constructor
		public Node(E elem, Node<E> prev, Node<E> next) {
			data = elem;
			this.prev = prev;
			this.next = next;
		}

	}

	private Node<E> head; // head of List
	private Node<E> tail; // tail of list
	private int size; // number of nodes in list
	private ArrayList<Node<E>> indices; // list of nodes at given indices

	// constructor to create an empty double-linked list.
	public IDLList() {
		head = null;
		tail = null;
		size = 0;
		indices = new ArrayList<Node<E>>();
	}

	/*
	 * method that adds elem at position index (counting from wherever head is). It
	 * uses the index for fast access.
	 */
	public boolean add(int index, E elem) {
		// validate index
		if (index >= 0 && index <= size) {
			if (index == 0) // insert at head
			{
				add(elem);
			} else if (index == size) // insert at tail
			{
				append(elem);
			} else {
				// get the node at index
				Node<E> curr = indices.get(index);
				// create a new node with given elem, prev = curr's prev and next = curr
				Node<E> node = new Node<E>(elem, curr.prev, curr);
				// update the next of node previous to node to node
				node.prev.next = node;
				// update prev of curr
				curr.prev = node;
				// add node at index
				indices.add(index, node);
				// update nodes at index-1 and index+1
				indices.set(index - 1, node.prev);
				indices.set(index + 1, curr);
				size++;
			}

			return true; // valid index
		}
		// invalid index
		return false;
	}

	/*
	 * method that adds elem at the head (it becomes the first element of the list).
	 */
	public boolean add(E elem) {
		// create a new node with prev = null, next = head
		Node<E> node = new Node<E>(elem, null, head);

		// empty list, set tail to node
		if (head == null)
			tail = node;
		else { // non-empty list

			// set prev of head to node
			head.prev = node;
			// update first node in indices
			indices.set(0, head);
		}

		// update head to node
		head = node;

		// insert node at first index in indices
		indices.add(0, node);
		size++;
		return true;
	}

	/*
	 * method that adds elem as the new last element of the list (i.e. at the tail).
	 */
	public boolean append(E elem) {
		// create a new node with prev = tail, next = null
		Node<E> node = new Node<E>(elem, tail, null);
		tail.next = node; // update next of tail to node
		// update tail in indices
		indices.set(size - 1, tail);
		tail = node; // set tail to node
		// add node as the last element of indices
		indices.add(node);
		size++;
		return true;
	}

	/*
	 * method that returns the object at position index from the head. It uses the
	 * index for fast access. Indexing starts from 0, thus get(0) returns the head
	 * element of the list.
	 */
	public E get(int index) {
		// validates index
		if (index >= 0 && index < size) {
			// returns the node data at index
			return indices.get(index).data;
		} else // invalid index
			throw new IndexOutOfBoundsException("List index out of bounds.");
	}

	/*
	 * method that returns the object at the head
	 */
	public E getHead() {
		if (size > 0) // non-empty list
		{
			return head.data;
		} else // empty list
			throw new IllegalStateException("List is empty");

	}

	/*
	 * method that returns the object at the tail
	 */
	public E getLast() {
		if (size > 0) // non-empty list
		{
			return tail.data;
		} else // empty list
			throw new IllegalStateException("List is empty");
	}

	/*
	 * method that returns the list size
	 */
	public int size() {
		return size;
	}

	/*
	 * method that removes and returns the element at the head
	 */
	public E remove() {
		if (size > 0) // non-empty list
		{
			E data = head.data; // get the data at head
			head = head.next; // set head to node next to head
			size--;
			indices.remove(0); // remove the node at index 0 in indices
			if (head != null) // after removal list not empty
			{
				head.prev = null; // set prev of head to null
				indices.set(0, head); // update element at index 0 to head
			} else // empty list, set tail to null
				tail = null;

			return data;
		} else // empty list
			throw new IllegalStateException("List is empty");
	}

	/*
	 * method that removes and returns the element at the tail.
	 */
	public E removeLast() {
		if (size > 0) // non-empty list
		{
			E data = tail.data; // get the data at tail
			tail = tail.prev; // set tail to node prev of tail
			size--;
			indices.remove(size); // remove the last element of indices

			if (tail != null) // after removal list not empty
			{
				tail.next = null; // update next of tail to null
				indices.set(size - 1, tail); // update element at size-1 to tail
			} else // empty list, set head to null
				head = null;

			return data;
		} else // empty list
			throw new IllegalStateException("List is empty");
	}

	/*
	 * method that removes and returns the element at the index.
	 * 
	 */
	public E removeAt(int index) {
		// validate index
		if (index >= 0 && index < size) {
			if (index == 0) // remove head
				return remove();
			else if (index == size - 1) // remove tail
				return removeLast();
			else {
				// get the node at index
				Node<E> node = indices.get(index);
				indices.remove(index); // remove the node at index
				// update prev of new node at index to node prev to node
				indices.get(index).prev = node.prev;
				// update next of node at index-1 to node next to node
				indices.get(index - 1).next = node.next;
				// remove node from list
				node.next = null;
				node.prev = null;
				size--;
				return node.data;
			}
		} else // invalid index
			throw new IndexOutOfBoundsException("List index out of bounds.");
	}

	/*
	 * boolean remove(E elem) method that removes the first occurrence of elem in
	 * the list and returns true. Return false if elem is not in the list.
	 */
	public boolean remove(E elem) {
		// set curr to head
		Node<E> curr = head;
		int curr_index = 0; // set curr_index to 0

		// loop over list to search for elem
		while (curr != null) {
			if (curr.data.equals(elem)) // elem found, exit the loop
				break;
			curr = curr.next;
			curr_index++;
		}

		if (curr == null) // elem not in list
			return false;
		else // elem in list at curr_index, delete the node at curr_index
		{
			removeAt(curr_index);
			return true;
		}
	}

	/*
	 * toString() method to return string representation of the list
	 */
	public String toString() {
		String str = ""; // set str to empty string
		Node<E> curr = head; // set curr to head

		// loop over the list, appending data of nodes to str
		while (curr != null) {
			str += curr.data.toString();
			if (curr != tail) // not the last node
				str += " -> ";
			curr = curr.next;
		}

		return str;
	}

}// end of IDLList.java
