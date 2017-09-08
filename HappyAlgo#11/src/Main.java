import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Main {

    static final int MAX = 100;

    public static void main(String[] args) throws IOException {

        HashMap<Integer, ArrayList<Node>> map = new HashMap<>();

        // For loop - each pair repeats only once
        for (int a = 0; a < MAX; a++) {
            for (int b = a; b < MAX; b++) {

                int x = a * a * a + b * b * b;

                if (!map.containsKey(x)) {

                    map.put(x, new ArrayList<>());
                }

                Node node = new Node(a, b);
                map.get(x).add(node);
            }
        }

        Iterator<Integer> it = map.keySet().iterator();

        while (it.hasNext()) {

            int key = it.next();

            if (map.get(key).size() > 1) {

                System.out.println(key + ", " + map.get(key));
            }
        }
    }
}

class Node {

    int a, b;

    Node(int a, int b) {

        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {

        return "(" + a + ", " + b + ")";
    }
}
