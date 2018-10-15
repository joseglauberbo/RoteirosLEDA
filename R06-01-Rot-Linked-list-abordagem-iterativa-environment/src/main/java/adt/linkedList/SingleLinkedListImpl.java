package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return head.isNIL();
	}

	@Override
	public int size() {
		
		int tamanho = 0;
		
		if(!isEmpty()) {
			SingleLinkedListNode<T> auxiliar = this.head;
			
			while(!auxiliar.isNIL()) {
				tamanho += 1;
				auxiliar = auxiliar.getNext();
			}
		}
		
		return tamanho;
	}

	@Override
	public T search(T element) {
		
		if (element != null && !isEmpty()) {
			SingleLinkedListNode<T> auxiliar = this.head;
			while (!auxiliar.isNIL()) {
				if (auxiliar.getData().equals(element)) {
					return auxiliar.getData();
				}
				auxiliar = auxiliar.next;
			}
			
		}
		return null;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			SingleLinkedListNode<T> proximo = new SingleLinkedListNode<>();

			if (isEmpty()) {
				SingleLinkedListNode<T> novaCabeca = new SingleLinkedListNode<>(element, proximo);
				this.setHead(novaCabeca);
				
			} else {

				SingleLinkedListNode<T> auxiliar = this.getHead();
				
				while (!auxiliar.isNIL()) {
					auxiliar = auxiliar.getNext();
				}
				
				auxiliar.setData(element); 
				auxiliar.setNext(proximo);
			}
		}
	}


	@Override
	public void remove(T element) {
		
		if (element != null && !isEmpty()) {
			
			
			if (this.head.getData().equals(element)) {
				head = head.getNext();
			} else {
				SingleLinkedListNode<T> anterior = new SingleLinkedListNode<>();
				SingleLinkedListNode<T> auxiliar = this.getHead();
				
				while (!auxiliar.isNIL() && !auxiliar.getData().equals(element)) {
					
					anterior = auxiliar;
					auxiliar = auxiliar.getNext();
				}
				
				if(!auxiliar.isNIL()) {
					anterior.setNext(auxiliar.getNext());
				}
			}
		}
		
	}

	@Override
	public T[] toArray() {
		int index = 0;
		
		T[] array = (T[]) new Object[this.size()];
		
		SingleLinkedListNode<T> auxiliar = this.getHead();
		
		while(!auxiliar.isNIL()) {
			array[index] = auxiliar.getData();
			auxiliar = auxiliar.getNext();
			index += 1; 
		}
		
		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
