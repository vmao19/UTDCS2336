package BinaryTree;

import java.util.ArrayList;

public class BinaryTree implements Cloneable
{
    Node root = null;
    
    public Node getRoot()
    {   return root;    }
    
    public void setRoot(Node n)
    {   root = n;   }
    
    public boolean search(Node n)
    {
        Node cur = root;
        while (cur != null)
        {
            if(n.compareTo(cur) == 0)
                return true;
            else if(n.compareTo(cur) < 0)
		cur = cur.left;
            else
		cur = cur.right;
	}
	return false;	            
    }
    
    public void insert(Node n)
    {
        if (root == null)
        {
            root = n;
            return;
        }
        else
	{
        	Node cur = root;
               
       		while (true)
        	{
	            if(n.compareTo(cur) < 0)
			    {
					if (cur.left == null)
					{
						cur.left = n;
						return;
					}
		        	cur = cur.left;
			    }
		        else
			    {
		        	if (cur.right == null)
					{
						cur.right = n;
						return;
					}
		        	cur = cur.right;
				}
        	}
        }
   
    }
    
    public Node delete(Node n)
    {
        Node cur = root, parent = root;

        while (cur != null)
        {	
		//check if node is to the left
                if (n.compareTo(cur) < 0)
                {
                        parent = cur;
                        cur = cur.left;
                }
		//check if node is to the right
                else if (n.compareTo(cur) > 0)
                {
                        parent = cur;
                        cur = cur.right;
                }
		//break loop when node found
                else
                        break;
        }

	//check if node found - loop could end when cur == null
        if (cur != null)
        {
                //check if cur has 0 children
		if (cur.left == null && cur.right == null)
                    //make link from parent = null
		    if (parent.left == cur)
                            parent.left = null;
                    else
                            parent.right = null;
		//check if cur has 1 child on right
                else if(cur.left == null)
                {
		    //link proper parent link to child of cur
                    if (parent.left == cur)
                        parent.left = cur.right;
                    else
                        parent.right = cur.right;
		    //disconnect cur from tree
                    cur.right = null;
                }
		//check if cur has 1 child on left
                else if (cur.right == null)
                {
		    //link proper parent link to child of cur
                    if (parent.right == cur)
                        parent.right = cur.left;
                    else
                        parent.left = cur.left;
		    //disconnect cur from tree
                    cur.left = null;
                }
		//cur has 2 children
                else
                {
		    //parent will hold position where node will be copied
                    parent = cur;
		    //move cur to left child
                    cur = cur.left;
                    
		    //move cur down right branch of left subtree
                    while (cur.right != null)
                        cur = cur.right;
                    
		    //copy content from node to node
                    parent.num = cur.num;
		    //call delete again to delete the node that was copied
                    delete (cur);
                }
                        

        }
        return cur;
    }
    
    // Non-recursive clone method
    @Override
    public BinaryTree clone()
    {
    	ArrayList<Node> nodes = new ArrayList<Node>();
    	BinaryTree cloneBt = new BinaryTree();
    	
    	if(this.getRoot() != null)
    		nodes.add(this.getRoot());
    	else
    		return null;
    	
    	while (!nodes.isEmpty())
    	{
    		Node cur = nodes.remove(0);
    		cloneBt.insert(new Node(cur.getNum()));
    		
    		if (cur.getLeft() != null)
    			nodes.add(cur.getLeft());
    		if (cur.getRight() != null)
    			nodes.add(cur.getRight());	
    	}
    	
        return cloneBt;
    }
    
    // Recursive equals function
    public boolean equals(BinaryTree bt)
    {
    	return equalsRecursion(this.getRoot(), bt.getRoot());
    }
    
    private boolean equalsRecursion(Node thisNode, Node btNode)
    {
    	if (thisNode == null && btNode == null)
    		return true;
    	else if (thisNode == null || btNode == null)
    		return false;
    	else if (thisNode.compareTo(btNode) != 0)
    		return false;
    	else
    	{
    		return equalsRecursion(thisNode.getLeft(), btNode.getLeft()) && equalsRecursion(thisNode.getRight(), btNode.getRight());
    	}
    }
    
    
    /* Non-recursive equals method
    public boolean equals(BinaryTree bt)
    {
    	ArrayList<Node> thisNodes = new ArrayList<Node>();
    	thisNodes.add(this.getRoot());
    	
    	ArrayList<Node> btNodes = new ArrayList<Node>();
    	btNodes.add(bt.getRoot());
    	
    	while (!thisNodes.isEmpty() && !btNodes.isEmpty()) {
    		Node curThis = thisNodes.remove(0);
    		Node curBt = btNodes.remove(0);
    		
    		if (curThis.compareTo(curBt) != 0)
    			return false;
    		else {
    			if (curThis.getLeft() != null && curBt.getLeft() != null) {
    				thisNodes.add(curThis.getLeft());
    				btNodes.add(curBt.getLeft());
    			}
    			else if (curThis.getLeft() != null || curBt.getLeft() != null)
    				return false;
    			if (curThis.getRight() != null && curBt.getRight() != null) {
    				thisNodes.add(curThis.getRight());
    				btNodes.add(curBt.getRight());
    			}
    			else if(curThis.getRight() != null || curBt.getRight() != null)
    				return false;
    		}

    	}
    	return true;
    }
    */  
    
    
}