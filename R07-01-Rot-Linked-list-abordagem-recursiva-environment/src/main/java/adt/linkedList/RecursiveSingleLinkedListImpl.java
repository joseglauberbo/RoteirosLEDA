package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	public RecursiveSingleLinkedListImpl(T data,
			RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		return this.data == null;
	}

	@Override
	public int size() {

		int sum = 0;

		if (isEmpty()) {
			return sum;
		} else {
			sum++;
			return sum + next.size();
		}
	}

	@Override
	public T search(T element) {

		if (element != null) {
			T value = null;

			if (isEmpty()) {
				return value;
			} else if (this.data == element) {
				return data;
			} else {
				return next.search(element);
			}
		}
		return null;
	}

	@Override
	public void insert(T element) {

		if (element != null) {
			if (isEmpty()) {
				setData(element);
				this.next = new RecursiveSingleLinkedListImpl();
			} else {
				next.insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {

		if (element != null) {
			if(!isEmpty()) {
				if (this.data.equals(element)) {
					this.data = next.data;
					this.next = next.next;
				} else {
					this.next.remove(element);
				}
			}
		}
	}

	@Override
    public T[] toArray() {
        if(!isEmpty() && !isNill()) {
            T[] array = (T[]) new Object[this.size()];
            
            int index = 0;
            array[index] = this.getData();
            index++;

            T[] otherArray = this.getNext().toArray();

            for (int index2 = 0; index2 < otherArray.length; index2++) {
                array[index] = otherArray[index2];
                index++;
            }

            return array;
        }else{
            return (T[]) new Object[0];
        }
    }

	
	public boolean isNill() {
		return this.data == null;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
