package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		if (!node.isEmpty()) {

			BSTNode<T> pivot = (BSTNode<T>) node.right;
			pivot.parent = node.parent;
			node.parent = pivot;
			node.right = pivot.left;
			pivot.right.parent = pivot;
			pivot.left = node;
			return pivot;
		} 
		return null;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {

		if (!node.isEmpty()) {

			BSTNode<T> pivot = (BSTNode<T>) node.left;
			pivot.parent = node.parent;
			node.parent = pivot;
			node.left = pivot.right;
			pivot.left.parent = pivot;
			pivot.right = node;
			return pivot;
		} 
		return null;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
