/* 
Assignment number    :    9.0 
File Name            :    Node.java 
Name (First Last)    :    David Levinov  
Student ID           :    308152859
Email                :    davelevinov@gmail.com
*/
package mms;
/**
 * Represents a node in a doubly linked list. Each node points to a MemoryBlock object. 
 */
public class Node {

	// Three package-private fields should appear here.
	Node next;
	Node prev;
	MemoryBlock block; 

	/**
	 * Constructs a new node pointing to the given memory block
	 * 
	 * @param block
	 *        the given memory block
	 */
	public Node(MemoryBlock block) {
		this.block = block;
		prev = null;
		next = null;
	}
	
	/**
	 * A textual representation of this node, useful for debugging.
	 * (See the test output for examples).
	 */
	public String toString() {
		return ("" + block);
	}
}