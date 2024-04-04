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

    // Insertion START --------------------------------------------------------
    // public method for inserting into the tree.
    public void InsertItem(T key) {
	if (root == null) {
            root = new Node<T>(key); // create a new node using the key and set it to the empty root.
	} else {
            Insert(root, key); // Start recursive insertion procedure
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
    // Insertion END ----------------------------------------------------------

    // Deletion START --------------------------------------------------------
    // public accessible delete procedure
    public Node<T> DeleteItem(T key) {
        Node<T> deletedNode = null; // Node to return, returns default if tree is empty.
        if (root != null) {
            if (key.compareTo(root.GetKey()) == 0) {
                deletedNode = root;
                DeleteNode(root, root, false);
            } else {
                deletedNode = Delete(root, root, key, false); // returns null if Node with matching key is not found.
            }
        }
        return deletedNode; // return the now deleted node from the tree to the deletion requester
    }
    // Search portion of the delete procedure, looking for node with matching key supplied.
    private Node<T> Delete(Node<T> parent, Node<T> current, T key, boolean isLeft) {
        // if current is not null
	if (current != null) {
            if (key.compareTo(current.GetKey()) < 0) { // compare key with current's, if key is less than...
                parent = current; // update parent for next iteration
                return Delete(parent, current.GetLeft(), key, true); // recursive call for tree traversal
            } else if (key.compareTo(current.GetKey()) > 0) { // compare key with current's, if keu is greater than...
                parent = current; // update parent for next iteration
                return Delete(parent, current.GetRight(), key, false); // recursive call for tree traversal
            } else { // key is equivelent with current node
                DeleteNode(parent, current, isLeft); // node found, start node removal
                return current; // return the node to be deleted
            }
        }
        return null; // key not found if this is reached
    }
    // Actual deletion of node and takes care of BST Integrity.
    private void DeleteNode(Node<T> parent, Node<T> delNode, boolean isLeft) {
        // if node to delete has no leaves
        if (delNode.GetLeft() == null && delNode.GetRight() == null) {
            if (delNode == root) {
                root = null;
            } else if (isLeft) {
                parent.SetLeft(null); // Remove the left child reference
            } else {
                parent.SetRight(null); // Remove the right child reference
            }
        }
        // If node to delete has empty right leaf
        else if (delNode.GetRight() == null) {
            if(delNode == root) {
                // replace root with delNode's left
                root = delNode.GetLeft();
            } else if (isLeft) {
                // replace parent's left with delNode's left
                parent.SetLeft(delNode.GetLeft());
            } else {
                // replace parent's right with delNode's left
                parent.SetRight(delNode.GetLeft());
            }
        }
        // If node to delete has empty left leaf
        else if (delNode.GetLeft() == null) {
            if(delNode == root) {
                // replace root with delNode's right
                root = delNode.GetRight();
            } else if (isLeft) {
                // replace parent's left with delNode's right
                parent.SetLeft(delNode.GetRight());
            } else {
                // replace parent's right with delNode's right
                parent.SetRight(delNode.GetRight());
            }
        }
        // If node has a leaf on both sides
        else {
            Node<T> successor = GetSuccessor(delNode);
            // if node for deletion is the root of the tree
            if (delNode == root) {
                root = successor;
            }
            // if node for deletion is the left of parent
            else if (isLeft) {
                parent.SetLeft(successor);
            }
            // if node for deletion is the right of parent
            else {
                parent.SetRight(successor);
            }
            successor.SetLeft(delNode.GetLeft()); // successor cannot have left child for this here.
        }

    }
    // find a successor node in the case that the node to be deleted has both leaves filled
    private Node<T> GetSuccessor(Node<T> delNode) {
        Node<T> successorParent = delNode;
        Node<T> successor = delNode;
        Node<T> current = delNode.GetRight(); // go to right child
        while(current != null) {
            // left children,
            successorParent = successor;
            successor = current;
            current = current.GetLeft(); // go to left child
        } // if successor not
        
        if(successor != delNode.GetRight()) {
            // right child, make connections
            successorParent.SetLeft(successor.GetRight());
            successor.SetRight(delNode.GetRight());
        }

        return successor;
    }
    // Deletion END -----------------------------------------------------------

    // Search START -----------------------------------------------------------
    // Simple search based off the same logic used in `Node<T> Delete(Node<T>, Node<T>, T, boolean)`. 
    public boolean SearchItem(T key) {
        return Search(root, key);
    }
    // traverses current with recursion, returning the boolean of whether key was found in the tree or not.
    private boolean Search(Node<T> current, T key) {
        if (current != null) {
            if (key.compareTo(current.GetKey()) < 0) {
                return Search(current.GetLeft(), key);
            } else if (key.compareTo(current.GetKey()) > 0) {
                return Search(current.GetRight(), key);
            } else {
                return true;
            }
        }
        return false;
    }
    // Search END -------------------------------------------------------------

    // used to display the tree.
    public void DisplayTree() {
        inOrder(root);
    }
    // just a simple inorder traversal to display the tree.
    private void inOrder(Node localRoot) {
        if(localRoot != null) {
            inOrder(localRoot.GetLeft());
            System.out.print(localRoot.GetKey() + " ");
            inOrder(localRoot.GetRight());
        }
    }
}
