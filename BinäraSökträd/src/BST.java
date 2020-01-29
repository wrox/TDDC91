public class BST {
	/* Root of BST */
	private Node root;
	/* Number of nodes in BST */
	private int size;

	public BST() {
		this.root = null;
		this.size = 0;
	}

	/**
	 * Is the BST empty?
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Return root of BST
	 */
	public Node getRoot() {
		return root;
	}

	/**
	 * Return number of key-value pairs in BST
	 */
	public int size() {
		return size;
	}

	/**
	 * Does there exist a key-value pair with given key?
	 */
	public boolean contains(int key) {
		return find(key) != null;
	}

	/**
	 * Return value associated with the given key, or null if no such key exists
	 */
	public String find(int key) {
		return find(root, key);
	}

	/**
	 * Return value associated with the given key, or null if no such key exists
	 * in subtree rooted at x
	 */
	private String find(Node x, int key) {
		if (x == null) {
			return null;
		}
		if (key < x.key) {
			return find(x.left, key);
		} else if (key > x.key) {
			return find(x.right, key);
		} else {
			return x.val;
		}
	}

	/**
	 * Insert key-value pair into BST. If key already exists, update with new
	 * value
	 */
	public void insert(int key, String val) {
		if (val == null) {
			remove(key);
			return;
		}
		root = insert(root, key, val);
	}

	/**
	 * Insert key-value pair into subtree rooted at x. If key already exists,
	 * update with new value.
	 */
	private Node insert(Node x, int key, String val) {
		if (x == null) {
			size++;
			return new Node(key, val);
		}
		if (key < x.key) {
			x.left = insert(x.left, key, val);
		} else if (key > x.key) {
			x.right = insert(x.right, key, val);
		} else {
			x.val = val;
		}

		return x;
	}

	/**
	 * Remove node with given key from BST
	 */
	public void remove(int key) {		
		
		if (root == null) { // if the tree is empty
			return;
		} else {
			root = remove(root, key); // recursive function
		}
		
		return;
	} 

	private Node remove(Node rt, int key) { // works recursively
		if (rt.key < key) {
			if (rt.right == null) {
				return rt; // if the key does not exist
			}
			rt.right = remove(rt.right, key);
		} else if (rt.key > key) {
			if (rt.left == null) {
				return rt; // if the key does not exist
			}
			rt.left = remove(rt.left, key);
		} else { // found it
			if (rt.right == null) {
				size--;
				return rt.left; // replace with left child
			} else if (rt.left == null) {
				size--;
				return rt.right; //replace with right child
			} else { // two children
				Node temp = findmax(rt.left); // find the biggest node in the left subtree
				rt.key = temp.key; // replace the node with what you found
				rt.val = temp.val;
				size--;
				rt.left = removemax(rt.left); //delete the biggest node in the left subtree
			}
		}
		return rt;
				
	}
	
	private Node findmax(Node rt) {
		while (rt.right != null) { //the right child will be bigger,
			rt = rt.right; // loop until there isn't a right child
		}
		return rt; // return the biggest node
	}
	
	private Node removemax(Node rt) { //works recursively
		if (rt.right == null) {
			return rt.left; // when you've found the biggest node, replace it with its' left child
		}
		rt.right = removemax(rt.right); //work your way down to the right until you find the biggest node
		return rt;
	}

	public PreorderIterator preorder() {
		return new PreorderIterator(root);
	}

	public LevelorderIterator levelorder() {
		return new LevelorderIterator(root);
	}
}
