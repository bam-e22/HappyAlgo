class Node {

	private int value;
	private Node leftNode;
	private Node rightNode;

	public Node(int value) {

		this.setValue(value);
		this.setLeftNode(null);
		this.setRightNode(null);
	}

	public Node getRightNode() {
		return rightNode;
	}

	public void setRightNode(Node rightNode) {
		this.rightNode = rightNode;
	}

	public Node getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(Node leftNode) {
		this.leftNode = leftNode;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}