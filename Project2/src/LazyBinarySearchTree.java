import java.util.ArrayList;
public class LazyBinarySearchTree {
	
	  class TreeNode {			
		  private int key;
		  private TreeNode leftChild;
		  private TreeNode rightChild;
		  private boolean deleted;
		//TreeNode class
		  
		  public TreeNode(int key) {		
			this.key = key;
			leftChild = null;
			rightChild = null;
			deleted = false;
		}
		//TreeNode constructor
		  
		public TreeNode getLeft() {		
			  return leftChild;
		  }
		  public TreeNode getRight() {
			  return rightChild;
		  }
		  public int getKey() {
			  return key;
		  }
		  public boolean isDeleted() {
			  return deleted;
		  }
		  public void setRight(TreeNode rightChild) {
			  this.rightChild = rightChild;
		  }
		  public void setLeft(TreeNode leftChild) {
			  this.leftChild = leftChild;
		  }
		  public void setDeleted(boolean d) {
		  deleted = d;
		  }
		  public boolean getDeleted() {
			  return deleted;
		  }
	  }
	//Setters and getters for the varibales within treeNode
	  
	  private TreeNode root;		
	  String[] strArray3 = new String[900]; 
	  	 //string array to store the postorder traversal
	  LazyBinarySearchTree(){		
		//lazybinary tree root is null
		  root = null;
	  }
	  public boolean insert(int key) throws IllegalArgumentException {		
		  try {
			if(key > 99 || key < 1)		
		//if key is not within the bounds throw exception
				throw new IllegalArgumentException();
		  }
		  catch (IllegalArgumentException e) {		
			//catches and handles exception
			  return false;
		  }
	      
	          return insert(root, key);		
	        //call insert method with key as the int
	   }
	  
	  private boolean insert(TreeNode n,int key) {		
		 TreeNode temp = n;				
		//temp to traverse n;
		 TreeNode h  = n;				
		//head to keep head of n;
		 
		  if(root == null) {
			 root = new TreeNode(key);
			 return true;  
			
		  }
		  if(temp.getRight() == null && key > n.getKey()) {		
			//if only root exists then see if the node goes left or right
			  TreeNode t = new TreeNode(key);
				 n.setRight(t);
				 return true;
		  }
		  
		  if(temp.getLeft() == null && key < n.getKey()) {
			  TreeNode t = new TreeNode(key);
				 n.setLeft(t);
				 return true;
		  }
		  
		  while(temp.getRight() != null || temp.getLeft() != null) {	
			//while loop to get the next nodes
		  if(key > temp.getKey() && temp.getRight() != null) {		
			//decide where to insert the new node
			  temp = temp.getRight();
			  }
		  
		 if (key < temp.getKey() && temp.getLeft() != null) {
			  temp = temp.getLeft();
		  }
		 
		 if(key == temp.getKey()) {		
			//if key == the current node then undelete the node unlesss its already undeleted
			 if(temp.getDeleted() == false)
				 return false;
			 else {
			n= temp;
			n.setDeleted(false);
			n = h;
			return true;
			 }
		 }
		 
		 if(temp.getLeft()== null && key < temp.getKey()) {		
			 TreeNode t = new TreeNode(key);
			 n = temp;
			 n.setLeft(t);
			 n =h;
			 return true;
		 }
		//place node as left child
		 
		 if(temp.getRight()== null && key > temp.getKey()) {
			 TreeNode t = new TreeNode(key);
			 n =temp;
			 n.setRight(t);
			 n = h;
			 return true;
		 }
		  }
		 return false;
		 //place node as right child
}
	  
	  public int findMin() {	
		  if (root == null)
			  return -1;
		  else return minVal(root);
	  }
	//findMin to call minVal method
	  
	  int minVal(TreeNode n) {
		  TreeNode t = n;
		  while(t.getLeft() != null) {		
			//left node always has lower value so while loop
			  if(t.getLeft().getDeleted()) {	
				//if left is deleted then check for its child nodes to see if they have a min value
				  if(t.getLeft().getRight() != null) {
					  return t.getLeft().getRight().getKey();
					  }
				  else if(t.getLeft().getLeft() != null)
					  return t.getLeft().getLeft().getKey();
				  else 
				  return t.getKey();	
				//otherwise the parent node is returned
			  }
			  else
			  t = t.getLeft();		
			//next node
		  }
		  return t.getKey();
	  }
	  
	  
	  public int findMax() {
		  int res = 0;
		  if (root == null)
			  return -1;
		  else return maxVal(root, res);
	  }
	  
	  
	  public int maxVal(TreeNode r, int res) {
		  if(r != null) {		
			//checks for root null
		  res = r.getKey();
	        int lres = maxVal(r.getLeft(), res);
	        int rres = maxVal(r.getRight(), res);
	  
	        if (lres > res)
	            res = lres;
	        if (rres > res)
	            res = rres;
		  }
	        return res;
	  }
	  
	  
	  int count = 0;	
	//count to keep count of the string array
	  
	  
	  void printPreorder(TreeNode node) {
	       if(node !=null) {
	    	   if(node.getDeleted()) {		
	    		 //if node is deleted then print with * else print without the * (both cases store the key in the array
	    	 strArray3[count] = "*" +node.getKey() + " ";
	    		   count++;
	    	   }
	    	   else {						
	        strArray3[count] = node.getKey() + " ";
	      //stores array to strArray3
 		   count++;
	    	   }
	        printPreorder(node.getLeft());
	        printPreorder(node.getRight());
	       }
	      
	        }
	 
	  
	  public String[] returnArr() {		
		//string class to pass the array and reset the count
		  count =0;
		  return strArray3;
	  }
	  
public String toString() {		
	 if(root == null)
		return "NODE IS EMPTY";
	printPreorder(root); 
	//calls printpreorder	
    return "ALl DONE!";
}

public boolean delete(int k) {
	if (root == null)			
		return false;
	else
	return	deleteKey(root, k);
	//calls deleteKey
}

private boolean deleteKey(TreeNode n, int key) {
	TreeNode temp = n;
	 while(temp.getRight() != null || temp.getLeft() != null || temp != null) {		
		//while temp is not null run the code
		 
		 if(key > temp.getKey())			
			 temp = temp.getRight();
		
		 if (temp == null) {
				return false;
			 }
		//if the key is greater than or less then traverse if it is equal then set deleted
		 if(key < temp.getKey())
			 temp = temp.getLeft();
		 
		 if(temp.getKey() == key && temp != null) {
			temp.setDeleted(true);
			
			return true;
		 }
	 }
	 return false;
}
public boolean contains(int key) throws IllegalArgumentException{
if (root == null)
	return false;

if(key > 99 || key <1) {	
	int[] myNumbers = {1, 2, 3};		
	//works same as insert method
	try {
		System.out.println(myNumbers[10]); 
	}
	catch(IllegalArgumentException e) {
		System.out.println("Error in insert: IllegalArgumentException raised");
		return false;
	}
	
return false;
}
	else
	return containsKey(root, key);
	
}
public boolean containsKey(TreeNode n, int key)	  {
	TreeNode temp = n;
	 while(temp.getRight() != null || temp.getLeft() != null || temp != null) {		
		
		 if(key > temp.getKey())		
			 temp = temp.getRight();
		//if temp is less/equal then traverse accordingly
		 if (temp == null) {
				return false;
			 }
		 if(key < temp.getKey())
			 temp = temp.getLeft();
		 if(temp.getKey() == key && temp != null) {		
			//if key is met then check if it has been deleted
			 if (temp.getDeleted())
			 return false;
			 else
				 return true;
		 }
	 }
	 return false;
	
	
}

public int height() {		
	if (root== null)
		return 0;
	else return heightTree(root, 0,0);
}
//gets height

public int heightTree(TreeNode node, int l, int r) {
	if(node.getLeft()!= null)
	l= heightTree(node.getLeft(), l, r);	
	//checks left and right children
	
	if(node.getRight()!=null)
    r = heightTree(node.getRight(),l,r);
    
    if (l > r)
        return (l+1);
     else
        return (r + 1);
	
}
public int size() {		
	if (root==null)
		return 0;
	else
		return sizeTree(root);
}
//size method

public int sizeTree(TreeNode r) {
	if(r !=null)
		return(sizeTree(r.getLeft()) + sizeTree(r.getRight()));	
	else
		return 1;
}
//gets the size of the tree by adding the nodes
	  
}