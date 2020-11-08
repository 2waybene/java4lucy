//package ml4lucy;

import java.util.Stack;

class Node 
{ 
    int key; 
    Node left, right; 
  
    public Node(int item) 
    { 
        key = item; 
        left = right = null; 
    } 
} 


public class BinaryTree {

	// Root of Binary Tree 
    Node root; 
  
    BinaryTree() 
    { 
        root = null; 
    } 
    
    /* Given a binary tree, print its nodes according to the 
    "bottom-up" postorder traversal. */
  void printPostorder(Node node) 
  { 
      if (node == null) 
          return; 

      // first recur on left subtree 
      printPostorder(node.left); 

      // then recur on right subtree 
      printPostorder(node.right); 

      // now deal with the node 
      System.out.print(node.key + " "); 
  } 

  /* Given a binary tree, print its nodes in inorder*/
  void printInorder(Node node) 
  { 
      if (node == null) 
          return; 

      /* first recur on left child */
      printInorder(node.left); 

      /* then print the data of node */
      System.out.print(node.key + " "); 
      /* now recur on right child */
      printInorder(node.right); 
  } 

  	/* Given a binary tree, print its nodes in preorder*/
  	void printPreorder(Node node) 
  	{ 
  		if (node == null) 
          return; 

      	/* first print data of node */
      	System.out.print(node.key + " "); 

      	/* then recur on left sutree */
      	printPreorder(node.left); 

      	/* now recur on right subtree */
      	printPreorder(node.right); 
  	} 
  
  

  	/*
	 	An iterative process to print preorder traversal of Binary tree 
  	 	This is what people learn from class, but it does the same job 
  	 	like the "printPreorder" method, however the "printPreorder"
  	 	involves a "SMART" recursive thinking.
  	 * */
  	 
	void iterativePreorder(Node node) 
	{ 

		// Base Case 
		if (node == null) { 
			return; 
		} 

		// Create an empty stack and push root to it 
		Stack<Node> nodeStack = new Stack<Node>(); 
		nodeStack.push(root); 

		/* Pop all items one by one. Do following for every popped item 
    	a) print it 
    	b) push its right child 
    	c) push its left child 
    	Note that right child is pushed first so that left is processed first */
		while (nodeStack.empty() == false) { 

       // Pop the top item from stack and print it 
       Node mynode = nodeStack.peek(); 
       System.out.print(mynode.key + " "); 
       nodeStack.pop(); 

       // Push right and left children of the popped node to stack 
       if (mynode.right != null) { 
           nodeStack.push(mynode.right); 
       } 
       if (mynode.left != null) { 
           nodeStack.push(mynode.left); 
       } 
   }
	}
  
  
  // Wrappers over above recursive functions 
  void printPostorder()  		{     printPostorder(root);  } 
  void printInorder()    		{     printInorder(root);    } 
  void printPreorder()   		{     printPreorder(root);   } 
  void printiterativePreorder() { iterativePreorder(root);   } 

  // Driver method 
  public static void main(String[] args) 
  { 
      BinaryTree tree = new BinaryTree(); 
      tree.root = new Node(1); 
      tree.root.left = new Node(2); 
      tree.root.right = new Node(3); 
      tree.root.left.left = new Node(4); 
      tree.root.left.right = new Node(5); 

      System.out.println("Preorder traversal of binary tree is "); 
      tree.printPreorder(); 

      System.out.println("\nInorder traversal of binary tree is "); 
      tree.printInorder(); 

      System.out.println("\nPostorder traversal of binary tree is "); 
      tree.printPostorder(); 
      
      System.out.println("\niterativePreorder traversal of binary tree is "); 
      tree.printiterativePreorder(); 
  } 
} 

	
