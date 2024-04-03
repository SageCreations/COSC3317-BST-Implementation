public class Node<T extends Comparable<T>> {
    private T key;
    private Node<T> left;
    private Node<T> right;
    
    public Node() {
        key = null;
        left = null;
        right = null;
    }

	public Node(T key) {
		this.key = key;
		left = null;
		right = null;
	}
    
    public void SetKey(T key) { this.key = key; }
	public T GetKey() { return key; }
    
    public void SetLeft(Node<T> left) { this.left = left; }
    public Node<T> GetLeft() { return left; }
    
    public void SetRight(Node<T> right) { this.right = right; }
    public Node<T> GetRight() { return right; }

    public void DisplayNode() {
        System.out.print(key.toString());
    }
}
