package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (element == null) return;
		if (this.isFull()) throw new QueueOverflowException();
		
		if (this.isEmpty()) {
			this.head = 0;
			this.tail = 0;
			this.array[0] = element;
			elements += 1;
		} else {
			this.tail = (this.tail + 1) % array.length;
			this.array[tail] = element;
			elements += 1;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.isEmpty()) throw new QueueUnderflowException();
		
		T value = array[head];
		
		if (this.head == this.tail) {
			this.head = -1;
			this.tail = -1;
			this.elements -= 1; 
		} else { 
			this.head = ((this.head + 1) % array.length);
			this.elements -= 1;
		}
		
		return value;
	}

	@Override
	public T head() {
		if (this.isEmpty()) return null;
		return this.array[head];
	}

	@Override
	public boolean isEmpty() {
		return (this.elements == 0);
	}

	@Override
	public boolean isFull() {
		return (this.elements == array.length);
	}

}
