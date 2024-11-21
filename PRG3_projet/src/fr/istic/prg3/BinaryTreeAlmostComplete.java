/**
 * 
 */
package fr.istic.prg3;

import java.util.Objects;



/**
 * @version 1.0
 *
 */
public class BinaryTreeAlmostComplete {
	
	protected int rootValue;
	protected BinaryTreeAlmostComplete left;
	protected BinaryTreeAlmostComplete right;
	protected BinaryTreeAlmostComplete up;
	protected int nbDescendants;
	
	
	public BinaryTreeAlmostComplete(int value) {
		this(value, null);
	}
	
	
	public BinaryTreeAlmostComplete(int[] values) {
		this(values[0], null);
		for(int i = 1; i < values.length; i++)addValue(values[i]);
	}
	
	
	public BinaryTreeAlmostComplete(int value, BinaryTreeAlmostComplete parent) {
		this.rootValue = value;
		this.left = null;
		this.right = null;
		this.up = parent;
		this.updateNumberOfDescendants();
	}
	
	
	
	
	
	
	public void addValue(int value) {
		if (Objects.isNull(this.left)) {
			this.left = new BinaryTreeAlmostComplete(value, this);
			this.updateNumberOfDescendants();
		}
		else {
			if (Objects.isNull(this.right)) {
				this.right = new BinaryTreeAlmostComplete(value, this);
				this.updateNumberOfDescendants();
			}
			else {
				// both left and right exist
				int nbDescLeft = this.left.nbDescendants;
				if (getLevels(nbDescLeft) == getLevels(nbDescLeft + 1)) {
					// the lowest level of left child is not full
					this.left.addValue(value);
				}
				else {
					// the lowest level of left child is full
					int nbDescRight = this.right.nbDescendants;
					if (nbDescLeft > nbDescRight) {
						// the lowest level of left child is full, AND the lowest level of right child is not full
						this.right.addValue(value);
					}
					else {
						// both left and right child are full and have the same level
						this.left.addValue(value);
					}
				}
			}
		}
	}
	
	
	
	protected static int getLevels(int n) {
		return (int)(Math.log(n + 1) / Math.log(2));
	}
	
	
	protected BinaryTreeAlmostComplete getRightmostLowestNode() {
		if (!Objects.nonNull(this.left))return this;
		if (!Objects.nonNull(this.right))return this.left;
		if(getLevels(this.left.nbDescendants) == getLevels(this.left.nbDescendants + 1)) {
			return this.left.getRightmostLowestNode();
		}else if(getLevels(this.right.nbDescendants) == getLevels(this.right.nbDescendants + 1)) {
			
			return this.right.getRightmostLowestNode();
			
		}
		if(this.left.nbDescendants > this.right.nbDescendants)return this.left.getRightmostLowestNode();
		return this.right.getRightmostLowestNode();
	}
	
	
	
	
	public void siftDown() {
		BinaryTreeAlmostComplete current = this;
		while(Objects.nonNull(current.left)) {
			
			int temp = current.rootValue;
			if(current.rootValue < current.left.rootValue) {
				current.rootValue = current.left.rootValue;
				current.left.rootValue = temp;
				current = current.left;
			}else {
				if(Objects.nonNull(this.right) && this.rootValue < this.right.rootValue) {
					current.rootValue = current.right.rootValue;
					current.right.rootValue = temp;
					current = current.right;
				}else break;
			}
			
		}
	}
	
	
	public void siftUp() {
		BinaryTreeAlmostComplete current = this;
		while(Objects.nonNull(current.up)) {
			int temp = current.rootValue;
			if(current.rootValue > current.up.rootValue) {
				current.rootValue = current.up.rootValue;
				current.up.rootValue = temp;
				current = current.up;
			}else break;
		}
	}
	
	public String toString() {
		return toString("");
	}
	
	
	public String toString(String offset) {
		StringBuilder chaine = 	new StringBuilder();
		offset += offset + "  ";
		chaine.append(this.rootValue + " (" + this.nbDescendants + " descendants)");
		if (Objects.nonNull(this.left)) {
			chaine.append("\n" + offset  + this.left.toString(offset));
		}
		if (Objects.nonNull(this.right)) {
			chaine.append("\n" + offset + this.right.toString(offset));
		}
		
		return chaine.toString()
;	}
	
	
	
	protected void updateNumberOfDescendants() {
		this.nbDescendants = 0;
		if (Objects.nonNull(this.left)) {
			this.nbDescendants += 1 + this.left.nbDescendants;
		}
		if (Objects.nonNull(this.right)) {
			this.nbDescendants += 1 + this.right.nbDescendants;
		}
		if (Objects.nonNull(this.up)) {
			up.updateNumberOfDescendants();
		}
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] values = {109,107,111,112,103,104,110,101,106,102,108};
		BinaryTreeAlmostComplete tree = new BinaryTreeAlmostComplete(values);
		BinaryTreeAlmostComplete rightTree = tree.getRightmostLowestNode();
		System.out.println(rightTree.toString());
	}

}
