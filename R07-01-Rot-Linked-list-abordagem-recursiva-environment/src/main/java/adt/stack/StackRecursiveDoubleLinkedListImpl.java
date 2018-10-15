package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackRecursiveDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new RecursiveDoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (element != null) {
			if (isFull()) {
				throw new StackOverflowException();
			} else {
				top.insertFirst(element);
			}
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (isEmpty()) {
			throw new StackUnderflowException();
		} else {
			T valueOfElement = ((RecursiveDoubleLinkedListImpl<T>) top).getData();
			top.removeFirst();
			return valueOfElement;
		}
	}

	@Override
	public T top() {
		if (isEmpty()) {
			return null;
		} else {
			return ((RecursiveDoubleLinkedListImpl<T>) top).getData();
		}
	}

	@Override
	public boolean isEmpty() {
		return top.isEmpty(); 
	}

	@Override
	public boolean isFull() {
		return size == top.size();
	}

}
