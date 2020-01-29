import java.util.Iterator;

public class PreorderIterator implements Iterator<Node> {
	protected Stack<Node> s;

	public PreorderIterator(Node tree) {
		s = new Stack<Node>();
		if (tree != null)
			s.push(tree);
	}

	public void remove() {
		// Leave empty
	}

	public Node next() {
		Node node = s.pop(); // start with the root
		
		if (node.right != null) // if there is a right child, add it to the stack
			s.push(node.right);
		if (node.left != null) // same with left child. The left child will always be on top 
			s.push(node.left); // of the right child and as such will be printed first along with its subtree.
		
		return node;
	}

	public boolean hasNext() {
		return !s.isEmpty();
	}
}
