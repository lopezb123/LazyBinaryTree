
import java.io.*;
import java.util.Scanner;
import java.io.PrintWriter;
public class BinaryTreeCreator {

	public static void main(String[] args) throws FileNotFoundException {
		LazyBinarySearchTree tree = new LazyBinarySearchTree();
String c;		//the variables
String subString = null;

int end;
int key;

Scanner input = new Scanner(new File("lazy.txt"));		
String fileName = "out.txt";							
PrintWriter outputStream = new PrintWriter(fileName);
//creates necessary items

while(input.hasNext()) {		
c = input.next();		
end = c.indexOf(":");
if(end != -1)
subString = c.substring(0,end);		
else
	subString = c;
//reads the input and prevents any errors in reading the file

if(!subString.contentEquals("Insert") && !subString.contentEquals("Delete") && !subString.contentEquals("PrintTree") && !subString.contentEquals("Contains") && !c.contentEquals("FindMin") && !c.contentEquals("FindMax") && !c.contentEquals("Height") && !c.contentEquals("Size") ) {
	outputStream.println("Error in Line: " + subString);		
}
//verifies the input
if(subString.contentEquals("Insert")) {
	if(c.lastIndexOf(":")+1 == 0) 
		outputStream.println("Error in Line: " + subString);
		else {
	key = Integer.parseInt(c.substring(c.lastIndexOf(":")+1));
	//displays error message if it meets the requirements, otherwise, key will be assigned a value
	if(tree.insert(key)) 
	outputStream.println("True");
	
	else
	outputStream.println("Error in insert: IllegalArgumentException raised");
	
		}
	//prints out true unless an error is met, where it will raise an exception
	}
if(subString.contentEquals("Delete")) {
	key = Integer.parseInt(c.substring(c.lastIndexOf(":")+1));		
	if(tree.delete(key))
		outputStream.println("True");
	else 
		outputStream.println("False");
}
	//if key the item is found then it will print out true, otherwise it will display false
//
if(subString.contentEquals("PrintTree")) {
	tree.toString();
	String arr[] = tree.returnArr();		

	for(int i=0; arr[i] != null;i++)	
	outputStream.print(arr[i]);

outputStream.println();
}
//stores the tree in an array and traverses through it, printing out the contents in the process.

if(subString.contentEquals("Contains")) {			
	key =Integer.parseInt(c.substring(c.lastIndexOf(":")+1));
	
	if(tree.contains(key))
		outputStream.println("True");
	else
		outputStream.println("False");
	}
//checks for the value asked

if(c.contentEquals("FindMin")) {		
	outputStream.println(tree.findMin());
}

if(c.contentEquals("FindMax"))
	outputStream.println(tree.findMax());

if(c.contentEquals("Height")) 
	outputStream.println(tree.height()-1);

if(c.contentEquals("Size"))
	outputStream.println(tree.size()-1);
}
//the code above finds either the minimum value, maximum value, height of the tree, or size of it.

outputStream.close();
//ends program
	}
}
