package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private static final int ZERO = 0;


	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		tail = -1;
	}

	@Override
	public T head() {
		if (this.isEmpty()) return null;
		return this.array[ZERO];
	}

	@Override
	public boolean isEmpty() {
		return (this.tail == -1);
	}

	@Override
	public boolean isFull() {
		return (this.tail == array.length -1);
	}

	private void shiftLeft() {
		for (int index = ZERO; index < this.tail; index++) {
			array[index] = array[index+1];
		}
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (element == null) return;
		if (this.isFull()) throw new QueueOverflowException();
		
		this.tail++;
		this.array[this.tail] = element;
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.isEmpty()) throw new QueueUnderflowException();

		T value = this.array[ZERO];
		this.shiftLeft();
		this.tail--;

		return value;
	}

}
