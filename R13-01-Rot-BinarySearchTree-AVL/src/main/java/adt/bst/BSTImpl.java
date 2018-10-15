package adt.bst;

import java.util.ArrayList;
import java.util.List;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {

		root = new BSTNode<T>();
		root.setLeft(new BSTNode<T>());
		root.setRight(new BSTNode<T>());
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		if (this.isEmpty()) {
			return -1;
		}
		return this.heightAux(this.root, -1);

	}
	
	public int heightAux(BSTNode<T> node, int height) {

		if (!node.isEmpty()) {

			int left = 0;
			int right = 0;
			
			if (node.getLeft() instanceof BSTNode) {

				
				BSTNode<T> esquerda = (BSTNode<T>) node.getLeft();
				left = this.heightAux(esquerda, height + 1);
			}

			if (node.getRight() instanceof BSTNode) {

				BSTNode<T> direita = (BSTNode<T>) node.getRight();
				right = this.heightAux(direita, height + 1);
			}
			height = Math.max(left, right);
		}

		return height;
	}

	private BSTNode<T> searchAux(BSTNode<T> node, T element) {

		if (!node.isEmpty()) {

			if (node.getData().equals(element)) {
				return node;	
			}
			
			if (node.getData().compareTo(element) > 0) {
				BSTNode<T> left = (BSTNode<T>) node.getLeft();
				return searchAux(left, element);

			}
			else if (node.getData().compareTo(element) < 0) {
				BSTNode<T> right = (BSTNode<T>) node.getRight();
				return searchAux(right, element);
			}
		}

		return node;
	}

	@Override
	public BSTNode<T> search(T element) {

		if (element != null) {

			return searchAux(this.root, element);
		}
		return null;
	}


	@Override
	public void insert(T element) {

		if (element != null) {

			insertAux(this.root, null, element);
		}
	}

	private void insertAux(BSTNode<T> node, BSTNode<T> otherNode, T element) {
		
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.setParent(otherNode);
			
		} else {
			if (node.getData().compareTo(element) > 0) {
				BSTNode<T> left = (BSTNode<T>) node.getLeft();
				this.insertAux(left, node, element);
			} else if (node.getData().compareTo(element) < 0) {
				BSTNode<T> right = (BSTNode<T>) node.getRight();
				this.insertAux(right, node, element);
			}
		}
	}

	private BSTNode<T> maximumAux(BSTNode<T> node) {

		if (node.isEmpty()) {
			return null;
		}
		
		if (node.getRight().isEmpty()) {
			return node;
		}
		if (node.getRight() instanceof BSTNode) {
			BSTNode<T> right = (BSTNode<T>) node.getRight();
			return this.maximumAux(right);
		}
		return null;
	}

	@Override
	public BSTNode<T> maximum() {
		if (this.isEmpty()) {
			return null;
		}
		return maximumAux(this.root);
	}

	private BSTNode<T> minimumAux(BSTNode<T> node) {
		if (node.isEmpty()) {
			return null;
		}

		
		if (node.getLeft().isEmpty()) {
			return node;
		}
		if (node.getLeft() instanceof BSTNode) {
			BSTNode<T> left = (BSTNode<T>) node.getLeft();
			return this.minimumAux(left);
		}
		return null;

	}

	@Override
	public BSTNode<T> minimum() {

		if (this.isEmpty()) {
			return null;
		}

		return minimumAux(this.root);
	}

	@Override
	public BSTNode<T> sucessor(T element) {

		BSTNode<T> node = search(element);

		return sucessorAux(node);

	}

	protected BSTNode<T> sucessorAux(BSTNode<T> node) {

		BSTNode<T> otherNode = (BSTNode<T>) node.getParent();

		if (node.isEmpty()) {
			return null;
		}

		if (!node.getRight().isEmpty()) {
			BSTNode<T> right = (BSTNode<T>) node.getRight();
			return minimumAux(right);

		}

		while (otherNode != null && node.equals(otherNode.getRight())) {
			node = otherNode;
			otherNode = (BSTNode<T>) otherNode.getParent();
		}

		return otherNode;

	} 
	
	@Override
	public BSTNode<T> predecessor(T element) {

		BSTNode<T> node = search(element);
		BSTNode<T> otherNode = (BSTNode<T>) node.getParent();

		if (node.isEmpty()) {
			return null;
		}
		if (!node.getLeft().isEmpty()) {

			BSTNode<T> left = (BSTNode<T>) node.getLeft();
			return maximumAux(left);
		}
		while (otherNode != null && node.equals(otherNode.getLeft())) {
			node = otherNode;
			otherNode = (BSTNode<T>) otherNode.getParent();
		}

		return otherNode;

	}

	@Override
	public void remove(T element) {

		if (!this.isEmpty()) {
			BSTNode<T> node = this.search(element);

			if (!node.isEmpty()) {
				this.removeAuxMethod(node);
			}
		}
	}

	private void removeAuxMethod(BSTNode<T> node) {


		if (node.isLeaf()) {

			if (this.size() == 1) {
				node.setData(null);
			}

			else if (filhoEsquerda(node)) {
				BSTNode<T> newOne = new BSTNode<T>();
				newOne.setParent(node.getParent());
				node.getParent().setLeft(newOne);
			} else if (filhoDireita(node)) {
				BSTNode<T> newOne = new BSTNode<T>();
				newOne.setParent(node.getParent());
				node.getParent().setRight(newOne);
			}
		}


		else if (childCount(node) == 1) {

			if (!node.getLeft().isEmpty()) {

				if (filhoEsquerda(node)) {
					node.getParent().setLeft(node.getLeft());
					node.getLeft().setParent(node.getParent());
				} else if (filhoDireita(node)) {
					node.getParent().setRight(node.getLeft());
					node.getLeft().setParent(node.getParent());

				} else {

					if (!node.getLeft().isEmpty()) {
						this.root = (BSTNode<T>) node.getLeft();
						node.getLeft().setParent(null);
					} else if (!node.getRight().isEmpty()) {
						this.root = (BSTNode<T>) node.getRight();
						node.getRight().setParent(null);
					}
				}

			} else if (!node.getRight().isEmpty()) {

				if (filhoEsquerda(node)) {
					node.getParent().setLeft(node.getRight());
					node.getRight().setParent(node.getParent());
				} else if (filhoDireita(node)) {
					node.getParent().setRight(node.getRight());
					node.getRight().setParent(node.getParent());
				}

				else {
					if (!node.getLeft().isEmpty()) {
						this.root = (BSTNode<T>) node.getLeft();
						node.getLeft().setParent(null);
					} else if (!node.getRight().isEmpty()) {
						this.root = (BSTNode<T>) node.getRight();
						node.getRight().setParent(null);
					}
				}
			}
		}

		else if (childCount(node) == 2) {
			BSTNode<T> sucessor = sucessorAux(node);
			node.setData(sucessor.getData());
			removeAuxMethod(sucessor);
		}

	}

	protected boolean filhoEsquerda(BSTNode<T> node) {

		if (node.getParent() != null) {
			if (node.equals(node.getParent().getLeft())) {
				return true;
			}
		}

		return false;
	}

	protected boolean filhoDireita(BSTNode<T> node) {

		if (node.getParent() != null) {
			if (node.equals(node.getParent().getRight())) {
				return true;
			}
		}

		return false;
	}

	protected int childCount(BSTNode<T> node) {

		int children = 0;

		if (!node.getLeft().isEmpty()) {
			children++;
		}
		if (!node.getRight().isEmpty()) {
			children++;
		}
		return children;
	}

	private void adiciona(List<T> list, BSTNode<T> node) {
		list.add(node.getData());
	}

	@Override
	public T[] preOrder() {

		int i = 0;
		T[] array = (T[]) new Comparable[this.size()];
		List<T> otherList = new ArrayList<T>();

		preOrder(otherList, this.root);

		for (T element : otherList) {

			array[i] = element;
			i++;

		}

		return array;
	}

	private void preOrder(List<T> list, BSTNode<T> node) {

		if (!node.isEmpty()) {
			adiciona(list, node);
			
			BSTNode<T> left = (BSTNode<T>) node.getLeft();
			BSTNode<T> right = (BSTNode<T>) node.getRight();

			if (!left.isEmpty()) {
				preOrder(list, left);
			}
			if (!right.isEmpty()) {
				preOrder(list, right);
			}
		}
	}

	@Override
	public T[] order() {

		int i = 0;
		T[] array = (T[]) new Comparable[this.size()];
		List<T> otherList = new ArrayList<T>();

		inOrderAux(otherList, this.root);

		for (T element : otherList) {

			array[i] = element;
			i++;

		}

		return array;
	}

	private void inOrderAux(List<T> list, BSTNode<T> node) {

		if (!node.isEmpty()) {

			BSTNode<T> left = (BSTNode<T>) node.getLeft();

			if (!left.isEmpty()) {

				inOrderAux(list, left);
			}

			adiciona(list, node);

			BSTNode<T> right = (BSTNode<T>) node.getRight();

			if (!right.isEmpty()) {

				inOrderAux(list, right);
			}

		}

	}

	@Override
	public T[] postOrder() {

		int i = 0;
		T[] array = (T[]) new Comparable[this.size()];
		List<T> otherList = new ArrayList<T>();

		postOrderAux(otherList, this.root);

		for (T element : otherList) {

			array[i] = element;
			i++;

		}

		return array;
	}

	private void postOrderAux(List<T> list, BSTNode<T> node) {

		if (!node.isEmpty()) {

			BSTNode<T> left = (BSTNode<T>) node.getLeft();
			BSTNode<T> right = (BSTNode<T>) node.getRight();

			if (!left.isEmpty()) {

				postOrderAux(list, left);
			}

			if (!right.isEmpty()) {

				postOrderAux(list, right);
			}

			adiciona(list, node);
		}

	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}