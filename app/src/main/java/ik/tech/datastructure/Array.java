package ik.tech.datastructure;

class Array    {
	private  int arr[];
	private int nElement;
	private int size;

	@SuppressWarnings("unchecked")
	public Array(int s) {
		size = s;
		arr = new int[size];
		nElement = 0;
	}
	public int getNelement() {
		return nElement;
	}//end getNelement()


	public String insertInSorted(Integer data){

		if(nElement>=size)
		{
			return "array is full";
		}
		int i=nElement-1;
		try {
			while (i >= 0 && data <= arr[i] ) {
				arr[i + 1] = arr[i];
				i--;
			}
			arr[i + 1] = data;
			nElement++;
			return data + " inserted in sorted array ";
		}catch (IndexOutOfBoundsException e){
			return e.getMessage();
		}
	}//end insertInSorted()


	public String insertFirst(Integer data){

		if(nElement>=size)
			return "array is full";

		for(int i=nElement-1;i>=0;i--)
		arr[i+1]=arr[i];
		arr[0]=data;
		nElement++;
		return data +" inserted at first ";
	}//end insertFirst()

	public String insertLast(Integer data) {

		if(nElement>=size)
		{
			//JOptionPane.showMessageDialog(null,"array is full");
		    return "array is full";
		}
		arr[nElement++]=data;
		return data +" inserted at last ";
	}//end insert()
	
	public String insert(Integer data,int i) {

		if(nElement>=size)
		{
			//JOptionPane.showMessageDialog(null,"array is full");
		    return "array is full";
		}
		if(i>nElement||i<0)
		{

			return "invalid index";
		}
	
		for(int j=nElement-1;j>=i;j--)
			arr[j+1]=arr[j];
		arr[i]=data;
		nElement++;
		return data +" inserted at " +i+"th index";
	}//end insert()
	
	
	public String delete(Integer data) {

		if(nElement==0)
		{
			return "array is empty";
		}
		Integer index=null;
		for(int i=0;i<nElement;i++) {
			if (data.equals( arr[i])) {
				index = i;
				break;
			}
		}
		if(index==null)
			return "didn't find this data in array";

		for(int j=index;j<nElement-1;j++)
			arr[j]=arr[j+1];
		nElement--;
		return data.toString()+" is deleted";
	}//end delete()

	public String deleteFirst(){
		if(nElement==0)
			return "array is empty" ;

		Integer data= arr[0];
		for (int i=0;i<nElement;i++)
			arr[i]=arr[i+1];
		nElement--;
		return data+" is deleted";
	}//end deleteFirst()

	public String deleteLast(){
		if(nElement==0)
			return "array is empty" ;

		Integer data= arr[nElement-1];
		nElement--;
		return data+" is deleted";
	}//end deleteLast()

	public String deleteAt(int i) {
		if(nElement==0)
		{
			return "array is empty" ;
		}
		if(i>nElement-1||i<0)
		{

			return "invalid index" ;
		}
		Integer data=arr[i];
		for(int j=i;j<nElement-1;j++)
			arr[j]=arr[j+1];
		nElement--;
		return data+" deleted from "+i+"th index";
	}//end deleteAt()
	
	public String getAt(int index) {
		if(nElement==0)
			return"array is empty";

		if(index>nElement-1||index<0)
			return "invalid index";

		return arr[index]+" at "+index+"th index";
	}//end getAt()
	
	
	public String search(Integer data) {

		Integer index=null;
		for(Integer i=0;i<nElement;i++) {
			if(data.equals(arr[i])){
				index=i;
				break;
				}
		}
		if(index==null)
			return "data is not available";

		return data +" searched at "+index +"th index";
	}//end search()
/*
	 public int compare(x x1,  intx2) {
			int prio = 0;
		int length= Math.min(((String) x1).length(), ((String) x2).length());
			if (x1 instanceof String) {
					for (int i = 0; i < length; i++) {
						if (((String) x1).charAt(i) > (((String) x2).charAt(i)))
							return 1;
						else if (((String) x1).charAt(i) < (((String) x2).charAt(i)))
							return -1;
						else
							prio = 0;

					}
					if(prio==0) {
						if (((String) x1).length() > ((String) x2).length())
							prio = 1;
						else if (((String) x1).length() < ((String) x2).length())
							prio = -1;
					}

			} else if (x1 instanceof Integer) {
				if ((Integer) x1 > (Integer) x2)
					prio = 1;
				else if ((Integer) x1 < (Integer) x2)
					prio = -1;
				else
					prio = 0;
			}

			else if (x1 instanceof Double) {
				if ((Double) x1 > (Double) x2)
					prio = 1;
				else if ((Double) x1 < (Double) x2)
					prio = -1;
				else
					prio = 0;
			}

			else if (x1 instanceof Float) {
				if ((Float) x1 > (Float) x2)
					prio = 1;
				else if ((Float) x1 < (Float) x2)
					prio = -1;
				else
					prio = 0;
			}

			return prio;
		}
	*/
	public String bSearch(Integer item) {

		int beg = 0, end = nElement-1, mid = (beg + end) / 2;
		while (beg < end && arr[mid] != ( item)) {
			if (item <arr[mid])
				end = mid - 1;

			else
				beg = mid + 1;

			mid = (beg + end) / 2;
		} // end while loop
		if (arr[mid] != item)
			return "data is not available";
		else
			return arr[mid]+" searched at "+mid +"th index";
	}// end bSearch()

	public String traverse() {
		String data="";
		for (int i = 0; i < nElement;i++)
		{
		 data+=i+":"+arr[i]+"  ";
		
		}
		 return data;
	}// end traverse ()

	public void bubbleSort() {
		for (int i=0;i<nElement-1;i++) {
			for(int j=0;j<nElement-i-1;j++) {
				if(arr[j] > arr[j+1])
				{
					Integer temp =arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=temp;
				}//end if 
			}//end in for
		}//end out for
	}//end bubbleSort()
	
	public void selectionSort() {
		for (int i = 0; i < nElement-1; i++) {
			int imin = i;
			for (int j = i + 1; j < nElement; j++) {
				if (arr[j] < arr[imin])
					imin = j;
			} // end in for loop
			Integer temp = arr[imin];
			arr[imin] = arr[i];
			arr[i] = temp;
		} // end out for loop
	}// end selectionSort()
	public void insertionSort() {
		for (int i=1;i<nElement;i++) {
		
			Integer temp = arr[i];
			int j =i-1;
			while(j>=0 && temp < arr[j])
				{arr[j+1]=arr[j];
				j--;
				 }//end in loop
			arr[j+1]=temp;
		}//end out loop
	}//end insert()
	
	public void quickSort(int start,int end) {
	
			if(start>=end)
				return;
			
				int pivot=partition(start,end);
				quickSort(start,pivot-1);
				quickSort(pivot+1,end);
		
	}//end quickSort()
	
	public int partition(int start,int end) {
		Integer pivot=arr[end];
		int pIndex=start; 
		for(int i=start;i<end;i++) {
			if(arr[i] <= pivot ){
				Integer temp=arr[i];
				arr[i]=arr[pIndex];
				arr[pIndex]=temp;
				pIndex++;
			}//end if
		}//end for
		Integer temp=arr[end];
		arr[end]=arr[pIndex];
		arr[pIndex]=temp;
		return pIndex;
	}//end partition()

}//end Arry class
