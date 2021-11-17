package ik.tech.datastructure;


class node{
	int data;
	node right;
	node left;
	boolean isLeft;
	public node(int d) {
		data=d;
	}

}

class LinkList {

	node start;
	node end;
	LinkList() {
		start = null;
		end = null;

	}

	public String insertAt(int index, int item) {
		node  temp = new node(item);
		if (start == null) {
			start = temp;
			end = temp;

			return item +" is inserted at "+index+"th index";
		}

		node  curr = getAt(index - 1);
		if (index <= 0) {

			curr.left = temp;
			temp.right = curr;
			start = temp;


			return item +" is inserted at "+index+"th index";
		}
		if (curr.right == null) {

			curr.right = temp;
			temp.left = curr;
			end = temp;

			return item +" is inserted at "+index+"th index";
		}

		temp.left = curr;
		temp.right = curr.right;
		curr.right.left = temp;
		curr.right = temp;
		return item +" is inserted at "+index+"th index";
	}//end insertAt()

	public String insertLast(int item) {
		node  temp = new node (item);
		if (start == null) {
			start = temp;
			end = temp;
		} else {
			temp.left = end;
			end.right = temp;
			end = temp;
		}
		return item +" is inserted at first";
	}//end insertLast()
	public  String insertFirst(int item) {
		node  temp = new node (item);
		if (start == null) {
			start = temp;
			end = temp;
		} else {
			temp.right = start;
			start.left = temp;
			start = temp;
		}
		return item +" is inserted at first";
	}//end insertFirst()


	public String insertIn(int item) {
		node  temp = new node (item);
		if (start == null) {
			start = temp;
			end = temp;
			return item +" is inserted in sorted list";
		}

		node  curr = start;
		while (curr != null && !(curr.data >item ))  {
			curr = curr.right;
		}
		if (curr == null) {
			temp.left = end;
			end.right = temp;
			end = temp;
			return item +" is inserted in sorted list";
		}
		if (curr == start)
			start = temp;
		temp.right = curr;
		temp.left = curr.left;
		if (curr.left != null)
			curr.left.right = temp;
		curr.left = temp;
		return item +" is inserted in sorted list";
	}//end insertIn()


	public String deleteFirst() {
		if (isEmpty())
			return "list is empty";


		 int data = start.data;
		if (start == end) {
			end = null;
			start = null;
			return data+" is deleted from first of LinkedList";
		}
		start = start.right;
		start.left = null;

		 return data+" is deleted from first of LinkedList";
	}//end deleteFirst()

	public String deleteLast() {
		if (isEmpty())
			return "list is empty";

		 int data = end.data;
		if (start == end) {
			end = null;
			start = null;
			return data+" is deleted from last of LinkedList";
		}
		end = end.left;
		end.right = null;

		return data+" is deleted from last of LinkedList";
	}//end deleteLast()

	public String  deleteAt(int index) {
		if (isEmpty())
			return "list is empty";


		node  temp = getAt(index);
		 int item = temp.data;

		if (temp == start) {
			start = temp.right;
			if (start != null)
				start.left = null;
			return item+" is deleted from index "+index+"th";
		}
		if (temp == end) {
			end = temp.left;
			if (end != null)
				end.right = null;

			return item+" is deleted from index "+index+"th";
		}
		temp.right.left = temp.left;
		temp.left.right = temp.right;


		return item+" is deleted from index "+index+"th";

	}//end deleteAt()

	public String delete(int item) {
		if (isEmpty())
		return  "list is empty";

		node  temp = search(item);
		if (temp == null)
			return item+" is not found";

		if (temp == start) {
			start = temp.right;
			if (start != null)
				start.left = null;
			return  item +" is deleted";
		}
		if (temp == end) {
			end = temp.left;
			if (end != null)
				end.right = null;
			return  item +" is deleted";
		}
		temp.right.left = temp.left;
		temp.left.right = temp.right;

		return  item +" is deleted";
	}//end delete()

	public node  search(int item) {
		if (isEmpty()) {
			//JOptionPane.showMessageDialog(null, "list is empty");
			return null;
		}
		node  temp = start;
		while (temp.data != item) {

			if (temp.right == null) {
				return null;
			}
			temp = temp.right;
		}
		return temp;
	}//end search()

	public void insertionSort() {
		if (isEmpty())
			return;


		node  curr = start;
		start = null;
		while (curr != null) {
			insertIn(curr.data);
			curr = curr.right;
		}

	}//end insertionSort()

	public node  getAt(int index) {
		if (isEmpty())
			return null;

		int i = 0;
		node  temp = start;
		while (temp.right != null && i < index) {
			temp = temp.right;
			i++;
		}
		return temp;
	}//end getAt()

	public boolean isEmpty() {
		return start == null;
	}

	public String traverseForward() {
		if (isEmpty())
			return  "list is empty";
		node  temp = start;
		String s = "";
		while (temp != null) {
			s += temp.data + " ";
			temp = temp.right;
		}
		return s;
	}

	public void traverseBackward() {
		if (isEmpty()) {
			//JOptionPane.showMessageDialog(null, "list is empty");
			return;
		}
		node  temp = end;
		String s = "";
		while (temp != null) {
			s += temp.data + " ";
			temp = temp.left;

		}
		System.out.println(s);
	}
}