public class Tree<T extends Comparable<T>> {
	// Starting node for the tree.
	private Node<T> root;
	
	// Default constructor
    public Tree() {
        root = null;
    }
	// optional constructor with forced root
    public Tree(Node<T> root) {
        this.root = root;
    }
	// optional constructor with int that becomes a node then forced root.
	public Tree(T key) {
	   root = new Node<T>(key);
	}

    // public method for inserting into the tree.
    public void InsertItem(T key) {
		if (root == null) {
            root = new Node<T>(key);
		} else {
            Insert(root, key);
        }
    }
    //Recursive method for class's private use to find the next empty node for insertion
    private void Insert(Node<T> current, T key) {
        // Checks for the current node being null.
        if (current != null) {
            // compares key to be inserted with current's key, specifically whether key is less than current's key
            if (key.compareTo(current.GetKey()) < 0) {
                // if current's left is not null, we keep recursion going, otherwise we insert the new item into it.
                if (current.GetLeft() != null) {
                    Insert(current.GetLeft(), key);
                } else {
                    current.SetLeft(new Node<T>(key));
                }
            } else {
                // if current's right is not null, we keep recursion going, otherwise we insert the new item into it.
                if (current.GetRight() != null) {
                    Insert(current.GetRight(), key);
                } else {
                    current.SetRight(new Node<T>(key));
                }
            }
        }
	}

    
	public Node<T> DeleteItem(T key) {
        Node<T> deletedNode = null; // Node to return, returns default if tree is empty.
        if (root != null) {
            deletedNode = Delete(root, key); // returns null if Node with matching key is not found.
        }
        return deletedNode;
    }
    private Node<T> Delete(Node<T> current, T key) {
        if (current != null) {
            if (key.compareTo(current.GetKey()) < 0) {
                return Delete(current.GetLeft(), key);
            } else if (key.compareTo(current.GetKey()) > 0) {
                return Delete(current.GetRight(), key);
            } else {
                DeleteNode(current);
                return current;
            }
        }
        return null;
    }
    private void DeleteNode(Node<T> delNode) {
        Node<T> temp = delNode;
        if (delNode.GetLeft() == null) { // right child
            delNode = delNode.GetRight();
            delNode.SetRight(null);
            System.out.println("left was null");
        }
        else if(delNode.GetRight() == null) { // left child
            delNode = delNode.GetLeft();
            System.out.println("right was null");
        }
        else {
            System.out.println("GetPredecessor");
            temp = GetPredecessor(delNode.GetLeft());
            Delete(temp, delNode.GetKey());
        }
    }
    private Node<T> GetPredecessor(Node<T> delNode) {
        Node<T> successorParent = delNode;
        Node<T> successor = delNode;
        Node<T> current = delNode.GetRight(); // go to right child
        while(current != null) // until no more
        { // left children,
            successorParent = successor;
            successor = current;
            current = current.GetLeft(); // go to left child
        } // if successor not
        
        if(successor != delNode.GetRight()) // right child,
        { // make connections
            successorParent.SetLeft(successor.GetRight());
            successor.SetRight(delNode.GetRight());
        } return successor;
    }

	public boolean SearchItem(T key) {
        
        return false;
    }
    
    public void DisplayTree() {
        inOrder(root);
    }
    
    private void inOrder(Node localRoot) {
        if(localRoot != null) {
            inOrder(localRoot.GetLeft());
            System.out.print(localRoot.GetKey() + " ");
            inOrder(localRoot.GetRight());
        }
    }
}
