import java.util.Iterator;

public class LevelorderIterator implements Iterator<Node> {
	protected Queue<Node> q;

	public LevelorderIterator(Node tree) {
		q = new Queue<>();
		if (tree != null)
			q.enqueue(tree);
	}

	public void remove() {
		// Leave empty
	}

	public Node next() {
		Node node = q.dequeue(); //start with the root
		
		if(node.left != null) //if there is a right child, add it to the queue
			q.enqueue(node.left);
		if (node.right != null) // do the same thing with the right child
			q.enqueue(node.right);
		
		return node; // All of the children to a "generation" (level) will be added in turn, 
	}				// and after there are no more children the next level can be added.

	public boolean hasNext() {
		return !q.isEmpty();
	}
}
