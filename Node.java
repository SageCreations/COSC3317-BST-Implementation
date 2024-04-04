// generic Node class with Comparable interface
public class Node<T extends Comparable<T>> {
    private T key; // data being used for the tree
    private Node<T> left; // left leaf node
    private Node<T> right; // right leaf node

    // default constructor
    public Node() {
        key = null;
        left = null;
        right = null;
    }
    // constructor with key parameter
    public Node(T key) {
	this.key = key;
	left = null;
	right = null;
    }

    // getter/setter key
    public void SetKey(T key) { this.key = key; }
    public T GetKey() { return key; }

    // getter/setter Left node
    public void SetLeft(Node<T> left) { this.left = left; }
    public Node<T> GetLeft() { return left; }

    // getter/setter Right node
    public void SetRight(Node<T> right) { this.right = right; }
    public Node<T> GetRight() { return right; }

    // display the key
    public void DisplayNode() {
        System.out.print(key.toString());
    }
}
