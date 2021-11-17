package ik.tech.datastructure;


class Stack {
	private int top;
	private int cap;
	private Integer[] ch;

	@SuppressWarnings("unchecked")
	public Stack(int size) {
		top = -1;
		cap = size;
		ch = new Integer[cap];
	}

	public String push(Integer c) {

		if (top >= cap)
			return "stack is overflow";
		else {
			top++;
			ch[top] = c;
			return c+" is pushed to top";
		} // end else
	}// end insert

	public String pop() {
		if (isEmpty())
			return "stack is underflow";

		Integer temp = ch[top];
		top--;
		return temp+" piped form top";
	}

	public boolean isEmpty() {
			return top == -1;
	}
	public boolean isFull() {
		return top==cap-1;
	}
	public String peak() {

		if (isEmpty())
		return "stack is underflow";

		return ch[top] +" is top of stack";
	}
	
	public String traverse() {
		if(isEmpty())
			return "Stack is empty";
	    String data="";
	    for(int i=0;i<=top;i++)
	    	data=ch[i]+"\n"+data;
	    
	    return data;
	}
}// end Stack