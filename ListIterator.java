/* 
Assignment number    :    9.0 
File Name            :    ListIterator.java 
Name (First Last)    :    David Levinov  
Student ID           :    308152859
Email                :    davelevinov@gmail.com
*/
package mms;
/*
 * Represents an iterator of a linked list.
 */
public class ListIterator {

    // current position in the list (cursor)
    Node current;

    /** Constructs a list iterator,
     *  starting at the given node */
    public ListIterator(Node node) {
        current = node;
    }

    /** Checks if this iterator has more
     *  nodes to process */
    public boolean hasNext() {
        if (current.next != null)
        	return true;
        else
        	return false;
    }

    /** Returns the current element in the list
     * and advances the cursor */
    public MemoryBlock next() {
       Node currentNode = current;
       // returns last node (null) to avoid exception
       if (hasNext() == false)
    	   return current.block;
       current = current.next;
    	return currentNode.block; 
    }
}