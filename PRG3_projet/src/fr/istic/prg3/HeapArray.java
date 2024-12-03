package fr.istic.prg3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

/**
 * @version 1.0
 *
 */
public class HeapArray extends ArrayList<Integer> implements Heap {
	
	
	
	public HeapArray(int[] valuesArray) {
		for(int val : valuesArray) {
			this.add(val);
		}
		heapifyDown();
	}
	
	
	public void addValue(int newValue) {
		if(this.contains(newValue))return;
		this.add(newValue);
		siftUp();
	}
	
	
	public int getMax() {
		return this.get(0);
	}
	
	
	public int extractMax() {
		int max = this.get(0);
		if(this.size() == 1)return max;
		swap(0,this.size() - 1);
		this.remove(this.size() - 1);
		siftDown();
		return max;
	}
	
	
	protected void heapifyDown() {
		for(int i = this.size() - 1; i >= 0; i--) {
			siftUp(i);
		}
		
	}
	
	
	public static int[] heapsort(int[] unsortedValues) {
		int[] val = new int[unsortedValues.length];
		HeapArray heap = new HeapArray(unsortedValues);
		for(int i = unsortedValues.length - 1; i >= 0 ; i--)val[i] = heap.extractMax();
		return val;
	}
	
	
	
	protected int indexLeft(int position) {
		return (position * 2) + 1;
	}
	
	
	protected int indexRight(int position) {
		return (position * 2) + 2;
	}
	
	
	protected int indexUp(int position) {
		return (position - 1) / 2;
	}
	
	
	public void siftDown() {
		this.siftDown(0);
	}
	
	
	protected void siftDown(int position) {
		int valeur = this.get(position);
		int indexDroit = indexRight(position);
		int indexGauche = indexLeft(position);
		int size = this.size() - 1;
		if(indexGauche > size)return;
		int valGauche = this.get(indexGauche);
		if(indexDroit > size) {
			if(valGauche > valeur)swapIfLowerAndSiftDown(position,indexGauche);
		}else {
			
			int valDroite = this.get(indexDroit);
			if(valGauche > valeur || valDroite > valeur) {
				if(valDroite > valGauche) {
					swapIfLowerAndSiftDown(position,indexDroit);
				}else {
					swapIfLowerAndSiftDown(position,indexGauche);
				}
			}else return;
		}
	}
	
	
	public void siftUp() {
		this.siftUp(this.size() - 1);
	}
	
	
	protected void siftUp(int position) {
		int valeur = this.get(position);
		int indexUp = indexUp(position);
		if(this.get(indexUp) >= valeur)return;
		swapIfGreaterAndSiftUp(position,indexUp);
	}
	
	
	protected void swap(int index1, int index2) {
		int temp = this.get(index1);
		this.set(index1,this.get(index2));
		this.set(index2,temp);
	}
	
	
	protected void swapIfGreaterAndSiftUp(int index1, int index2) {
		swap(index1,index2);
		siftUp(index2);
		siftUp(index1);
	}
	
	
	protected void swapIfLowerAndSiftDown(int index1, int index2) {
		swap(index1,index2);
		siftDown(index2);
	}
	
	
	public String toString() {
		return toString("");
	}
	
	
	public String toString(String offset) {
		
		return "";
	}
	
	
	

	public static void main(String[] args) {
		System.out.println("CONSTRUCTION");
		Random ran = new Random();
		int total = 10;
		int[] val = new int[total];
		//ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0 ; i < total; i++)
			{
			int rand = ran.nextInt(10 - 0 + 1) + 0;
			if(rand == 0) {
				val[i] = rand;
			}else {
				val[i] = i;
			}
			
			}
		
		//for(int valu : val)System.out.println(valu);
		long t1 =System.nanoTime();
		//list.sort(null);
		int[] sortedVal = heapsort(val);
		long t2 =System.nanoTime();
		//for(int valu : list)System.out.println(valu);
		System.out.println(t2 - t1);

	}
}
