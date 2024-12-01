package fr.istic.prg3.test;

import org.junit.Test;
import fr.istic.prg3.*;
import static org.junit.Assert.assertTrue;
public class HeapTreeTest {
	int[] testVal={109, 107, 111, 112, 103, 104, 110, 113, 106, 102, 108, 105};
	int[] sortedVal={102,103,104,105,106,107,108,109,110,111,112,113};
	
	   @Test
		public void creation() {
			HeapTree heapTest = new HeapTree(testVal);
			assertTrue(heapTest.extractMax() == 113);
			assertTrue(heapTest.extractMax() == 112);
		}
	   
	   @Test
		public void addTest() {
			HeapTree heapTest = new HeapTree(testVal);
			heapTest.addValue(114);
			assertTrue(heapTest.extractMax() == 114);
			assertTrue(heapTest.extractMax() == 113);
		}
	   @Test
		public void sortTest() {
			int[] test = Heap.heapsort(testVal);
			for(int i = 0; i < test.length; i++) {
				assertTrue(test[i] == sortedVal[i]);
			}
		}
}
