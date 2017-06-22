import BinaryTree.*;
public class Main
{
	public static void main(String[] args)
	{
		BinaryTree bt = new BinaryTree();
		bt.setRoot(new Node(35));
		
		bt.insert(new Node(5));
		bt.insert(new Node(15));
		bt.insert(new Node(10));
		bt.insert(new Node(34));
		bt.insert(new Node(36));
		bt.insert(new Node(100));
		bt.insert(new Node(42));
		
		BinaryTree btClone = bt.clone();
		
		if (bt.equals(btClone))
			System.out.println("bt equals btClone.");
		else
			System.out.println("bt does not equal btClone.");
		
		BinaryTree diff = new BinaryTree();
		diff.setRoot(new Node(123));
		
		if (bt.equals(diff))
			System.out.println("bt equals diff.");
		else
			System.out.println("bt does not equal diff.");
		
	}
}