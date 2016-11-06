
public class BinaryTree {

	public Node root;
	public int depth;

	public void addNode(int v) {

		int depth = 0;

		if (root == null) {

			root = new Node(v);

			if (this.depth < depth)
				this.depth = depth;

		} else {

			if (v < root.getValue()) {

				root.setLeftNode(addNode(v, root.getLeftNode(), ++depth));
			} else if (v > root.getValue()) {

				root.setRightNode(addNode(v, root.getRightNode(), ++depth));
			}

		}

	}

	private Node addNode(int v, Node n, int depth) {

		if (n == null) {

			n = new Node(v);

			if (this.depth < depth)
				this.depth = depth;

			return n;

		} else {

			if (v < n.getValue()) {

				n.setLeftNode(addNode(v, n.getLeftNode(), ++depth));
			} else if (v > n.getValue()) {

				n.setRightNode(addNode(v, n.getRightNode(), ++depth));
			}
		}

		return n;

	}

	public void printTree() {

		printPreOrder(root);
	}

	private void printPreOrder(Node n) {

		if (n != null) {

			System.out.print(n.getValue() + " ");

			printPreOrder(n.getLeftNode());
			printPreOrder(n.getRightNode());
		} else {

		}
	}

}
