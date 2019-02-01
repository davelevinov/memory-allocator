/* 
Assignment number    :    9.0 
File Name            :    LinkedList.java 
Name (First Last)    :    David Levinov  
Student ID           :    308152859
Email                :    davelevinov@gmail.com
*/
package mms;
/**
 * Represents a list of Nodes. The list has a "first" pointer, which points to the first node in the list,
 * a "last" pointer, which points to the last node in the list, and a size, which is the number of nodes in the list.  
 */
public class LinkedList {

    // Three package-private fields come here.
	Node first;
	Node last;
	int size;
	/**
	 * Constructs a new doubly-connected linked list.
	 */ 
	public LinkedList () {
		first = new Node(null);
		last = new Node(null);
		first.next = last;
		last.prev = first;
		size = 0;
	}
	
	/**
	 * Gets the node located at the given index in this list. 
	 * 
	 * @param index
	 *        the index of the node to retrieve, between 0 and size
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 * @return the node at the given index
	 */		
	public Node getNode(int index) {
        if (index >= size || index < 0)
        	throw new IllegalArgumentException("Illegal input " + index);
		
        Node requested = first.next;
        for (int i = 0; i < index; i++){
        	requested = requested.next;
        }
        return requested;
	}
	
	/**
	 * Creates a new Node object that points to the given memory block, and inserts the
	 * node to this list immediately prior to the given index (position in this list).
	 * <p>
	 * If the given index is 0, the new node becomes the first node in this list.
	 * <p>
	 * If the given index equals the size of this list, the new node becomes the last 
	 * node in this list.
	 * 
	 * @param block
	 *        the memory block to be inserted into the list
	 * @param index
	 *        the index before which the memory block should be inserted
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 */
	public void add(int index, MemoryBlock block) {
        if (index > size || index < 0)
        	throw new IllegalArgumentException("Illegal input " + index);
        
        if (index == 0)
        	addFirst(block);
        else if (index == size)
        	addLast(block);
        else{
        	Node inserted = new Node(block);
        	// the node at the index that will be pushed forward
        	Node forward = getNode(index);
        	// the node that will be before the one we insert
        	Node previous = forward.prev;
        	inserted.prev = previous;
        	previous.next = inserted;
        	inserted.next = forward;
        	forward.prev = inserted;
        	size++;
        }       	
	}	

	/**
	 * Creates a new node with a reference to the given memory block, and appends it to the end of this list
	 * (the node will become the list's last node).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addLast(MemoryBlock block) {
		Node newlast = new Node(block);
		newlast.next = last;
		newlast.prev = last.prev;
		last.prev.next = newlast;
		last.prev = newlast;
		size++;
	}
	
	/**
	 * Creates a new node with a reference to the given memory block, and inserts it at the beginning of this list
	 * (the node will become the list's first node).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addFirst(MemoryBlock block) {
		Node newfirst = new Node(block);
		newfirst.next = first.next;
		newfirst.prev = first;
		first.next.prev = newfirst;
		first.next = newfirst;		
		size++;
	}

	/**
	 * Gets the memory block located at the given index in this list.
	 * 
	 * @param index
	 *        the index of the retrieved memory block
	 * @return the memory block at the given index
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public MemoryBlock getBlock(int index) {
		// if illegal index exception will be thrown from getNode method
		return getNode(index).block;
	}

	/**
	 * Gets the index of the node pointing to the given memory block.
	 * 
	 * @param block
	 *        the given memory block
	 * @return the index of the block, or -1 if the block is not in this list
	 */
	public int indexOf(MemoryBlock block) {
		// no blocks so doesn't exist
		if (size == 0)
			return -1;
		// first, dummy exclusive
		Node current = first.next;
		for (int i = 0; i < size; i++) {
			if (block.equals(current.block))
				return i;
					
			current = current.next;
		}
		return -1; 
	}

	/**
	 * Removes the given node from this list.	
	 * 
	 * @param node
	 *        the node that will be removed from this list
	 */
	public void remove(Node node) {
		remove(node.block);			
	}

	/**
	 * Removes from this list the node which is located at the given index.
	 * 
	 * @param index the location of the node that has to be removed.
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public void remove(int index) {
		// if exception, will be thrown by getNode
		Node removed = getNode(index);
		Node forward = removed.next;
		Node previous = removed.prev;
		previous.next = forward;
		forward.prev = previous;
		// not necessary but to mark the removed node
		removed.next = null;
		removed.prev = null;
		size--;
	}

	/**
	 * Removes from this list the node pointing to the given memory block.
	 * 
	 * @param block the memory block that should be removed from the list
	 * @throws IllegalArgumentException
	 *         if the given memory block is not in this list
	 */
	public void remove(MemoryBlock block) {
		int a = indexOf(block);
		if (a == -1)
			return;
		else
			remove(a);	
	}	

	/**
	 * Returns an iterator over this list, starting with the first element.
	 */
	public ListIterator iterator(){
		ListIterator lit = new ListIterator(first.next);
		return lit; 
	}
	
	/**
	 * A textual representation of this list, useful for debugging.
	 */
	public String toString() {
		Node printed = first.next;
		StringBuilder ans = new StringBuilder();
		for (int i = 0; i < size; i++){
			ans.append(printed + " ");
			printed = printed.next;
		}
		return ans.toString();
	}
}