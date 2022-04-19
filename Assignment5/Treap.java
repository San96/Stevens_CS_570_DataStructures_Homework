/**
 * 
 */
package Assignments;

import java.util.Random;
import java.util.Stack;

/**
 * @author Sangram Thorat CWID : 20007345 CS 570: Homework Assignment 5
 *
 */
public class Treap<E extends Comparable<E>> {
	/** Node Class for data, priority left and right child */
	private static class Node<E> {
		// Data fields
		public E data; // Key for the search
		public int priority; // random heap priority
		public Node<E> left; // left child
		public Node<E> right; // right child

		// Constructor
		public Node(E data, int priority) {
			if (data == null) {
				throw new IllegalArgumentException();
			} else {
				this.left = null;
				this.right = null;
				this.data = data;
				this.priority = priority;
			}
		}

		/** rotateRight performs a right rotation */
		public Node<E> rotateRight() {

			Node<E> temp = this.left;
			Node<E> left = temp.right;
			temp.right = this;
			this.left = left;
			return temp;
		}

		/** rotateRight performs a right rotation */
		public Node<E> rotateLeft() {
			Node<E> temp = this.right;
			Node<E> right = temp.left;
			temp.left = this;
			this.right = right;
			return temp;
		}

		/** toString for String representation of treap data */
		public String toString() {
			return this.data.toString();

		}
	}

	/** Data fields */
	private Random priorityGenerator;
	private Node<E> root;

	/** Constructors creates an empty treap */
	public Treap() {

		priorityGenerator = new Random();
		root = null;
	}

	/**
	 * creates an empty treap and initializes priorityGenerator using new
	 * Random(seed)
	 */
	public Treap(long seed) {

		priorityGenerator = new Random(seed);
		root = null;
	}

	/** reheap Method to maintain heap structure */
	public void reheap(Node<E> child, Stack<Node<E>> stack) {
		while (!stack.isEmpty()) {
			Node<E> parent = stack.pop();
			if (parent.priority < child.priority) {
				if (parent.data.compareTo(child.data) > 0) {
					child = parent.rotateRight();
				} else {
					child = parent.rotateLeft();
				}
				if (!stack.isEmpty()) {
					if (stack.peek().left == parent) {
						stack.peek().left = child;
					} else {
						stack.peek().right = child;
					}
				} else {
					this.root = child;
				}
			} else {
				break;
			}
		}
	}

	/** method to add a new node with given key and random priority */
	boolean add(E key) {
		return add(key, priorityGenerator.nextInt());
	}

	/** helper function for add */
	boolean add(E key, int priority) {
		if (root == null) {
			root = new Node<E>(key, priority);
			return true;
		} else {
			Node<E> n = new Node<E>(key, priority);
			Stack<Node<E>> stack = new Stack<Node<E>>();
			Node<E> localroot = root;
			while (localroot != null) {
				localroot.data.compareTo(key);
				if (localroot.data.compareTo(key) == 0) {
					return false;
				} else {
					if (localroot.data.compareTo(key) < 0) {
						stack.push(localroot);
						if (localroot.right == null) {
							localroot.right = n;
							reheap(n, stack);
							return true;
						} else {
							localroot = localroot.right;
						}
					} else {
						stack.push(localroot);
						if (localroot.left == null) {
							localroot.left = n;
							reheap(n, stack);
							return true;
						} else {
							localroot = localroot.left;
						}
					}
				}
			}
			return false;
		}
	}

	/**
	 * deletes the node with the given key from the treap and returns true. If the
	 * key not found return false.
	 */
	public boolean delete(E key) {
		if (find(key) == false || root == null) {
			return false;
		} else {
			root = delete(key, root);
			return true;
		}
	}

	/** helper function of delete function to delete a node with given key */
	private Node<E> delete(E key, Node<E> localroot) {
		if (localroot == null) {
			return localroot;
		} else {
			if (localroot.data.compareTo(key) < 0) {
				localroot.right = delete(key, localroot.right);
			} else {
				if (localroot.data.compareTo(key) > 0) {
					localroot.left = delete(key, localroot.left);
				} else {
					if (localroot.right == null) {
						localroot = localroot.left;
					} else if (localroot.left == null) {
						localroot = localroot.right;
					} else {
						if (localroot.right.priority < localroot.left.priority) {
							localroot = localroot.rotateRight();
							localroot.right = delete(key, localroot.right);
						} else {
							localroot = localroot.rotateLeft();
							localroot.left = delete(key, localroot.left);
						}
					}
				}
			}
		}
		return localroot;
	}

	/**
	 * Finds a node with the given key in the treap rooted at root and returns true
	 * if it finds it and false otherwise.
	 */
	private boolean find(Node<E> root, E key) {
		if (root == null) {
			return false;
		}
		if (key.compareTo(root.data) == 0) {
			return true;
		} else if (key.compareTo(root.data) < 0) {
			return find(root.left, key);
		} else {
			return find(root.right, key);
		}
	}

	/**
	 * Find method which finds a node with given key and returns true if finds else
	 * false
	 */
	public boolean find(E key) {
		if (key == null) {
			throw new NullPointerException("Key cannot null");
		}
		return find(root, key);
	}

	public String toString() {
		StringBuilder strbuilder = new StringBuilder();
		preOrderTraverse(root, 1, strbuilder);
		return strbuilder.toString();
	}

	/** function to display treap structure using toString method */
	private void preOrderTraverse(Node<E> node, int depthCount, StringBuilder strbuilder) {
		for (int i = 1; i < depthCount; i++) {
			strbuilder.append("  ");
		}
		if (node == null) {
			strbuilder.append("null\n");
		} else {
			strbuilder.append("(key = " + node.toString() + ", ");
			strbuilder.append("priority = ");
			strbuilder.append(node.priority);
			strbuilder.append(")");
			strbuilder.append("\n");
			preOrderTraverse(node.left, depthCount + 1, strbuilder);
			preOrderTraverse(node.right, depthCount + 1, strbuilder);
		}
	}

	/** main Method */
	public static void main(String[] args) {

		Treap<Integer> treap = new Treap<Integer>();
		System.out.println("Treap after insert all data: ");
		System.out.println("----------------------------------------------");
		System.out.println("");
		treap.add(4, 19);
		treap.add(2, 31);
		treap.add(6, 70);
		treap.add(1, 84);
		treap.add(3, 12);
		treap.add(5, 83);
		treap.add(7, 26);
		System.out.println(treap.toString());

		System.out.println("Deleting a Node with key 6: " + treap.delete(6)); // test for deleting a node
		System.out.println("Treap after deleting node with key 6");
		System.out.println("------------------------------------");
		System.out.println("");
		System.out.println(treap.toString());

		System.out.println("Check find(key) and find(Node<E>, key) function");
		System.out.println("-----------------------------------------------");
		System.out.println("find key 3 within node (4, 20) is = " + treap.find(new Node(4, 20), 3)
		+ " because the root here is not exist in root");
		System.out.println("find key 4 within root is = " + treap.find(4));
		System.out.println("find key 1 within root is = " + treap.find(1));
		System.out.println("find key 8 within root is = " + treap.find(8));
		System.out.println();

		System.out.println("Treap with character nodes: ");
		Treap charTest = new Treap<Character>();
		charTest.add('A', 11);
		charTest.add('B', 9);
		charTest.add('E', 25);
		charTest.add('G', 5);
		charTest.add('H', 6);
		charTest.add('K', 67);
		charTest.add('I', 75);
		System.out.println("Treap after insert all data: ");
		System.out.println("----------------------------------------------");
		System.out.println(charTest.toString());
		charTest.add('C', 27);
		System.out.println("Treap after adding node with key C");
		System.out.println("------------------------------------");
		System.out.println(charTest.toString());
		System.out.println("find key A within root is = " + charTest.find('A'));
		System.out.println("Deleting a Node with key I: " + charTest.delete('I'));
		System.out.println("Treap after deleting node with key I");
		System.out.println("------------------------------------");
		System.out.println(charTest.toString());

	}

}
