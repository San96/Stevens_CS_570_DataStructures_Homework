/**
 * 
 */
package Assignments;

/**
 * @author Sangram Thorat CWID : 20007345
 *
 */
//IDLListTest.java
public class IDLListTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IDLList<Integer> list = new IDLList<Integer>();
		// adds elem at position index
		list.add(0, 11);
		list.add(0, 12);
		list.add(1, 13);
		list.add(1, 14);
		list.add(2, 15);
		System.out.println("List Size after adding elements at position index : " + list.size());
		System.out.println(list);
		// adds elem at the head
		list.add(10);
		System.out.println("List Size after adding element at head : " + list.size());
		System.out.println(list);

		// adds elem at the tail
		list.append(16);
		System.out.println("List Size after append element : " + list.size());
		System.out.println(list);

		// returns the object at position index from the head
		System.out.println("Get element at index 4: " + list.get(4));

		// returns the object at the head
		System.out.println("Get Head: " + list.getHead());

		// returns the object at the tail
		System.out.println("Get Tail: " + list.getLast());

		// removes and returns the element at the index
		System.out.println("Remove index 4: " + list.removeAt(4));
		System.out.println("List Size afetr removing element at specified index: " + list.size());
		System.out.println(list);

		// removes the first occurrence of elem in List
		System.out.println("Remove 14: " + list.remove(14));
		System.out.println("List Size after removing specifird element " + list.size());
		System.out.println(list);

		System.out.println("Remove 14: " + list.remove(14));
		System.out.println("Size: " + list.size());
		System.out.println(list);

		// removes and returns the element at the head
		System.out.println("Remove: " + list.remove());
		System.out.println("List Size after removing head : " + list.size());
		System.out.println(list);

		// removes and returns the element at the tail
		System.out.println("Remove Last: " + list.removeLast());
		System.out.println("List Size after removing tail : " + list.size());
		System.out.println(list);

	}

}
