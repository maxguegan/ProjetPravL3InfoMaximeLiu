/**
 * 
 */
package fr.istic.prg3;

import java.util.Objects;



/**
 * @version 1.0
 *
 */
public class HeapTree extends BinaryTreeAlmostComplete implements Heap {
	
	public HeapTree(int value) {
		super(value);
	}
	
	
	public HeapTree(int value, HeapTree parent) {
		super(value,parent);
	}
	
	
	public HeapTree(int[] tab) {
		this(tab[0]);
		for(int i = 1; i < tab.length; i++)addValue(tab[i]);
	}
	
	
	
	public void addValue(int value) {
		super.addValue(value);
		BinaryTreeAlmostComplete newVal = super.getRightmostLowestNode();
		newVal.siftUp();
	}
	
	
	public int extractMax() {
		int max = this.rootValue;
		if(!Objects.nonNull(this.left))return max;
		BinaryTreeAlmostComplete supVal = super.getRightmostLowestNode();
		this.rootValue = supVal.rootValue;
		supVal.rootValue = max;
		supVal = supVal.up;
		if(Objects.nonNull(supVal.right)) {
			supVal.right = null;
		}else {
			supVal.left = null;
		}
		this.siftDown();
		supVal.updateNumberOfDescendants();
		return max;
	}
	
	
	public int getMax() {
		return this.rootValue;
	}
	
	
	public static int[] heapsort(int[] unsortedValues) {
		int[] val =  new int[unsortedValues.length];
		HeapTree sortedTree = new HeapTree(unsortedValues);
		for(int i = unsortedValues.length - 1; i >= 0; i--) {
			val[i] = sortedTree.extractMax();
		}
		return val;
	}
	
	
	
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("CONSTRUCTION");
		int[] treeValues = {109, 107, 111, 112, 103, 104, 110, 113, 106, 102, 108, 105};
		
		int[] sortedVal = heapsort(treeValues);
		for(int val : sortedVal)System.out.println(val);
		System.out.println("\n");
		
		
		
	}

}
