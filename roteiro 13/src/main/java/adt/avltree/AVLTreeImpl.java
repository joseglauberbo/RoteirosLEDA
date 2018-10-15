package adt.avltree;

import java.lang.reflect.GenericArrayType;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		if (!node.isEmpty()) {
			BSTNode<T> nodeLeft = (BSTNode<T>) node.getLeft();
			BSTNode<T> nodeRight = (BSTNode<T>) node.getRight();
			return heightAux(nodeLeft, -1) - heightAux(nodeRight, -1);
		}

		return 0;	
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		BSTNode<T> newNodeLeft = (BSTNode<T>) node.getLeft();
		BSTNode<T> newNodeRight = (BSTNode<T>) node.getRight();
		int balance = calculateBalance(node);
		if (balance < -1 ) {
			if (calculateBalance((BSTNode<T>) node.getRight()) > 0)  {
				Util.rightRotation((BSTNode<T>)node.getRight());
			}
			Util.leftRotation((BSTNode<T>)node.getLeft());
		} else if (balance > 1) {
			if (calculateBalance((BSTNode<T>) node.getRight()) < 0) {
				Util.leftRotation((BSTNode<T>) node.getRight());
			}
			Util.rightRotation((BSTNode<T>) node.getRight());
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		if (node != null) {
			rebalance(node);
			rebalanceUp((BSTNode<T>) node.getParent());
		}
	}

	@Override
	public void insert(T element) {
		super.insert(element);
		BSTNode<T> node = search(element);
		rebalanceUp(node);
	}

}
