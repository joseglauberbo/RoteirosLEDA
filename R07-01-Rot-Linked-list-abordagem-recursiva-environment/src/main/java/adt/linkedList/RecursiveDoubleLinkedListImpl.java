package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {
	}

	public RecursiveDoubleLinkedListImpl(T data,
			RecursiveSingleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}

	@Override
	public void insert(T element) {

		if (element != null) {
			if (isEmpty()) {
				insertFirst(element);
			} else {
				this.next.insert(element);
			}
		}
	}

	@Override
	public void insertFirst(T element) {

			if (element != null) {
				RecursiveDoubleLinkedListImpl<T> secondNode = 
						new RecursiveDoubleLinkedListImpl(this.getData(), this.getNext(), this);
				this.setPrevious(new RecursiveDoubleLinkedListImpl(null, this, null));
				this.setData(element);
				this.setNext(secondNode);
				
			}
	}

	@Override
	public void remove(T element) {

		if (element != null) {
			if(!isEmpty()) {
				if (this.data.equals(element)) {
					removeFirst();
				} else {
					this.next.remove(element);
				}
			}
		}
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			RecursiveDoubleLinkedListImpl<T> newNode = (RecursiveDoubleLinkedListImpl<T>) this.getNext();
			RecursiveDoubleLinkedListImpl<T> newNode2 = (RecursiveDoubleLinkedListImpl<T>) newNode.getNext();
			if (newNode2 != null) {
				newNode2.setPrevious(this);
			}
			this.setData(newNode.getData());
			this.setNext(newNode2);
		}
	}


	@Override
	public void removeLast() {
		if (!isEmpty()) {
			if (this.getNext().isNill()) {
				this.setData(null);
				this.setNext(null);
			} else {
				((DoubleLinkedList<T>) this.getNext()).removeLast();
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
