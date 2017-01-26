import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * HappyAlgo#6 GreedyCoins
 */

public class Main {

    static final int N = 6;
    static final int[] coinValues = {1, 5, 10, 50, 100, 500};

    public static void main(String[] args) throws IOException {

        int A; // target
        int[] C; // coins
        int ans = 0; // result

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        A = Integer.parseInt(br.readLine());
        C = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {

            C[i] = Integer.parseInt(st.nextToken());
        }

        // A = 610
        // C = {3, 2, 1, 3, 0, 2}

        for (int i = 5; i >= 0; i--) {

            int temp = Math.min(A / coinValues[i], C[i]);

            A -= coinValues[i] * temp;

            ans += temp;
        }

        System.out.println(ans);
    }
}
