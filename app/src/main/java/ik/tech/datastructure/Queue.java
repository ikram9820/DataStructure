package ik.tech.datastructure;

class Queue  {
	private int size;
	private int front, rear;
	private Integer[] arr;

	public Queue(int size) {
		this.size = size;
		rear = front = -1;
		arr = new Integer[size];
	}

	public String enqueue(Integer item) {

		if (isFull())
			return "queue is full";
		else {
			if (isEmpty())
				rear = front = 0;
			else if (rear == size - 1)
				rear = 0;
			else
				rear++;
			arr[rear] = item;
			return item +" is inserted at last of queue";
		} // else
	}// end enqueue()

	public String dequeue() {
		if (isEmpty())
			return "queue is empty";

		Integer item = arr[front];
		if (rear == front)
			rear = front = -1;
		else if (front == size - 1)
			front = 0;
		else
			front++;
		return item+" is deleted from front of queue";
	}

	public boolean isEmpty() {
		return (front == -1);
	}

	public boolean isFull() {
		return (front == 0 && rear == size - 1 || front == rear + 1);
	}

	public String front() {
		if (isEmpty())
		return "queue is empty";

		return arr[front]+" fron of queue";
	}

	public String traverse() {
		
		String data=" ";
		if (isEmpty())
			System.out.println("queue is empty");
		else {
			int j = front;
			for (int i = 0; i < nElements(); i++) {
				if (j == size)
					j = 0;
				data=" -> "+ arr[j]+" "+data;
				j++;

			} // end for
		} // end else
		return data;
	}// end display()

	public int nElements() {
		return (size - front + rear + 1) % size;
	}// end nElements()

	
}// end Queue class