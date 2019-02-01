/* 
Assignment number    :    9.0 
File Name            :    Test.java 
Name (First Last)    :    David Levinov  
Student ID           :    308152859
Email                :    davelevinov@gmail.com
*/
package mms;
/* 
 * This program runs a test of the MMS system.
 * The test involves allocating and freeing some memory, and observing
 * the impact on the free list and the allocated list. If your system is 
 * well written, it should produce exactly the same outputs of this test.
 * 
 * Comment: you should treat this class as the ultimate test of your system.
 * Before using it, you must unit-test each step of your development separately.
 * For example, after writing each method in the LinkedList class, you must run
 * a separate test and make sure that your method is well implemented. In other
 * words, you must write your own localized testers and use them to test your work
 * gradually, as you make progress in your code development.
 */
public class Test {
	public static void main (String[] args) {
		MemorySpace m = new MemorySpace(1000);
		System.out.println(m + "\n");
		// Expected output:
		// (0 , 1000) 
		
		// Making some memory allocations:
		int[] addresses = new int[20];
		for (int i = 0; i < addresses.length; i++) {
			addresses[i] = m.malloc((int)Math.pow(i+2, 5)%123);
		}
		System.out.println(m + "\n");
		// Expected output:
		// (959 , 41) 
		// (0 , 32) (32 , 120) (152 , 40) (192 , 50) (242 , 27) (269 , 79) (348 , 50) (398 , 9) (407 , 1) (408 , 44) (452 , 3) (455 , 79) (534 , 68) (602 , 96) (698 , 1) (699 , 68) (767 , 42) (809 , 109) (918 , 32) (950 , 9) 
		
		// Freeing some memory:
		for (int i = 0; i < addresses.length; i+=2) {
			m.free(addresses[i]);
		}
		System.out.println(m + "\n");
		// Expected output:
		// (959 , 41) (0 , 32) (152 , 40) (242 , 27) (348 , 50) (407 , 1) (452 , 3) (534 , 68) (698 , 1) (767 , 42) (918 , 32) 
		// (32 , 120) (192 , 50) (269 , 79) (398 , 9) (408 , 44) (455 , 79) (602 , 96) (699 , 68) (809 , 109) (950 , 9) 
		
		/*
		// Freeing some more memory and defraging (IGNORE THIS TESTING SECTION)
		m.free(addresses[1]);
		m.free(addresses[3]);
		m.defrag();
		System.out.println(m);
		//expected output:
		//(959 , 41) (0 , 269) (348 , 50) (407 , 1) (452 , 3) (534 , 68) (698 , 1) (767 , 42) (918 , 32) 
		//(269 , 79) (398 , 9) (408 , 44) (455 , 79) (602 , 96) (699 , 68) (809 , 109) (950 , 9) 
		*/
		
	}
}