package ik.tech.datastructure;

import java.util.LinkedList;
import java.util.Queue;

class link{
	int data;
	link right;
	link left;

	int x,y;
	boolean isLeft=true;
	public link(int d) {
		data=d;
		x=200;
		y=0;
	}

}//end link class



public class Tree  {
	private link root;
	Queue<link> q ;
	public Tree() { root=null; }//end Tree()
	public  link getRoot(){ return root;}
	public String insert(int item) {
		link   temp = new link (item);
		if(isEmpty()) {
			root=temp;
			return item +" is inserted";
		}
		
		link  cur=root;
		while(cur!=null) {
			link   parent=cur;
			if(item < cur.data)
			{
				cur=cur.left;
				
				if(cur==null) {
					parent.left=temp;
					parent.left.isLeft=true;
				}
			}//end if
			else {
				cur=cur.right;
		
				if(cur==null) {
					parent.right=temp;
					parent.right.isLeft=false;
				}
			}//end else
		}//end while()
		return item +" is inserted";
	}//end insert()
	public String output;
	public String delete(int item) {
		if(isEmpty())
			return "Tree is empty";

		link  curr= root;
		link  parent= root ;
		boolean isLeft=true;
		while(curr.data!=item) {
			parent=curr;
			if(item< curr.data) {
				curr=curr.left;
				isLeft=true;
			}
			else {
				curr=curr.right;
				isLeft=false;
			}
			if(curr==null)
				return "link not found";

		}//end while()
//case 1
		if(curr.left==null&&curr.right==null) {
			if(curr==root)
				root=null;
			else if(isLeft)
				parent.left=null;
			else
				parent.right=null;
		}//end case 1
		
//case 2 right
		if(curr.left==null&&curr.right!=null) {
			if(curr==root)
				root=curr.right;
			else if(isLeft)
				parent.left=curr.right;
			else
				parent.right=curr.right;
		}//end case 2 right
//case 2 left 
		if(curr.left!=null&&curr.right==null) {
			if(curr==root)
				root=curr.left;
			else if(isLeft)
				parent.left=curr.left;
			else
				parent.right=curr.left;
		}//end case 2 left
//case 3
		if(curr.left!=null&&curr.right!=null) {
			link  min = getMin(curr.right);
			curr.data=min.data;
		
			link temp =curr;
			if(temp.right==min)
				temp.right=min.right;
			else {
				temp=temp.right;
				while(temp.left!=min)
					temp=temp.left;
			
			temp.left=min.right;
			
			}
		}//end case 3
		return item+" is deleted";
	}//end delete()

	public void bft() {
		q= new LinkedList();
		q.add(root);
		while(!q.isEmpty()) {
			link  temp=q.remove();
			if(temp.left!=null)
			q.add(temp.left);
			if(temp.right!=null)
			q.add(temp.right);
		}
	}
	
	public link  getMin(link  curr){

		while(curr.left!=null)
			curr=curr.left;
		
		return curr;
	}
	
	public String search(int item) {

		link  cur=root;
		while(cur.data!=item) {
			
			if(item < cur.data)
			{
				cur=cur.left;
			
			}//end if
			else {
				
				cur=cur.right;
				
			}//end else
			if(cur==null)
				return item +" is not found in tree";
		}//end while()
		return item +" is found in tree";
	}//end search()
	
	public void inorder(link   curr) {
		if(curr==null) 
			return;
		inorder(curr.left);
		output+=curr.data +" ";
		inorder(curr.right);		
	}//end inorder()

	public void preorder(link   curr) {
		if(curr==null)
			return;
		output+=curr.data +" ";
		inorder(curr.left);
		inorder(curr.right);
	}//end inorder()


	public void postorder(link   curr) {
		if(curr==null)
			return;
		inorder(curr.left);
		inorder(curr.right);
		output+=curr.data +" ";
	}//end inorder()

	public String display(int i) {
		output="";
		if(i==1)
			inorder(root);
		else if(i==2)
			preorder(root);
		else
			postorder(root);

		return output;
	}


	public boolean isEmpty() {
		return root==null;
	}//end isEmpty()
	
}//end Tree
