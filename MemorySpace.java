/* 
Assignment number    :    9.0 
File Name            :    MemorySpace.java 
Name (First Last)    :    David Levinov  
Student ID           :    308152859
Email                :    davelevinov@gmail.com
*/
package mms;

/**
 * Represents a managed memory space. The memory space is handled by managing a list 
 * of allocated memory blocks, and a list free memory blocks. Blocks move from one
 * list to the other as side effects of executing "malloc" and "free" function calls.
 */
public class MemorySpace {
	
	// A list representing all the memory blocks that are presently allocated
	private LinkedList allocatedList;

	// A list representing all the memory blocks that are presently free
	private LinkedList freeList;

	/**
	 * Constructs a new managed memory space of a given maximal size.
	 * Specifically, constructs an empty list of allocated blocks, 
	 * and a free list containing a single block which represents the entire memory
	 * space. The base address of this single block is zero, and its length is the
	 * given memory size.
	 * 
	 * @param maxSize
	 *            the size of the memory space to be managed
	 */
	public MemorySpace(int maxSize) {
		MemoryBlock entiremem = new MemoryBlock(0, maxSize);
		freeList = new LinkedList();
		freeList.addFirst(entiremem);
		allocatedList = new LinkedList();
	}

	/**
	 * Allocates a memory block of a requested length (in words). Returns the
	 * base address of the allocated block, or -1 if unable to allocate.
	 * 
	 * This implementation scans the freeList, looking for the first free memory block 
	 * whose length equals at least the given length. If such a block is found, the method 
	 * performs the following operations:
	 * 
	 * (1) A new memory block is constructed. The base address of the new block is set to
	 * the base address of the found free block. The length of the new block is set to the value 
	 * of the method's length parameter.
	 * 
	 * (2) The new memory block is appended to the end of the allocatedList.
	 * 
	 * (3) The base address and the length of the found free block are updated, to reflect the allocation.
	 * For example, suppose that the requested block length is 17, and suppose that the base
	 * address and length of the the found free block are 250 and 20, respectively.
	 * In such a case, the base address and length of of the allocated block
	 * are set to 250 and 17, respectively, and the base address and length
	 * of the found free block are set to 267 and 3, respectively.
	 * 
	 * (4) The new memory block is returned (to the caller, which is typically a constructor).
	 * 
	 * If we are lucky to find a block whose length is EXCATLY that of the requested length, 
	 * then the found block is removed from the freeList and appended to the allocatedList.
	 * 
	 * @param length
	 *        the length (in words) of the memory block that has to be allocated
	 * @return the base address of the allocated block, or -1 if unable to allocate
	 */
	public int malloc(int length) {
		ListIterator lit = freeList.iterator();
		MemoryBlock freeblock;
		MemoryBlock allocblock;
		while((freeblock = lit.next()) != null) {			
			if(freeblock.length > length) {
				allocblock = new MemoryBlock(freeblock.baseAddress, length);
				allocatedList.addLast(allocblock);
				freeblock.baseAddress += length;
				freeblock.length -= length;
				return allocblock.baseAddress;
			}	
			else if(freeblock.length == length) {
				allocblock = new MemoryBlock(freeblock.baseAddress, length);
				allocatedList.addLast(allocblock);
				freeList.remove(freeblock);
				return allocblock.baseAddress;
			}
		}
		return -1;
	}

	/**
	 * Frees the memory block whose base address equals the given address: deletes the block whose base address
	 * equals the given address from the allocatedList, and adds it at the end of the free list. 
	 * 
	 * @param baseAddress
	 *            the starting address of the block to freeList
	 */
	public void free(int address) {
		ListIterator lit = allocatedList.iterator();
		MemoryBlock allocblock;
		while((allocblock = lit.next()) != null) {
			if (allocblock.baseAddress == address) {
				freeList.addLast(allocblock);
				allocatedList.remove(allocblock);			
			}
		}
	}
	
	/**
	 * A textual representation of the current state of the free list and the allocated list, 
	 * using some sensible and easy to read format.
	 * (See the test output for examples).
	 */
	public String toString() {
		StringBuilder memstate = new StringBuilder();
		ListIterator alloclit = allocatedList.iterator();
		MemoryBlock appended;
		memstate.append(freeList + "\n");
		while (( appended = alloclit.next()) != null){
			memstate.append(appended + " ");
		}
		return memstate.toString(); 
	}
	
	/**
	 * Performs defragmantation of the memory space.
	 * (Should be called by malloc, when we run out of memory)
	 * This is an advanced and optional feature of the MMS, and we don't 
	 * expect you to implement it. You can simply ignore this method.
	 */
	public void defrag() {
	}
}
