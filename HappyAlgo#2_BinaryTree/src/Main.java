import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BinaryTree btree = new BinaryTree();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {

			btree.addNode(Integer.parseInt(st.nextToken()));
		}
		btree.printTree();

		System.out.println("\n" + btree.depth);

	}

}
