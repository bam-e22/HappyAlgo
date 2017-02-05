import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * HappyAlgo#9 knapsack Problem
 */

public class Main {

    static int N;
    static int W;
    static int[] w = new int[100];
    static int[] v = new int[100];
    static int[][] dp = new int[101][101];

    static {

        for (int i = 0; i < 101; i++) {

            Arrays.fill(dp[i], -1);
        }
    }

    public static void main(String[] args) throws IOException {

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            w[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }

        W = Integer.parseInt(br.readLine());

        // solve
        System.out.println(rec(0, W));

    }

    // i번째 이후의 무게의 총합이 j 이하일 때 value
    static int rec(int i, int j) {

        int res;

        if (dp[i][j] >= 0) {

            return dp[i][j];
        }

        if (i == N) {

            // 더 이상 물건이 없음
            res = 0;
        } else if (w[i] > j) {

            // 이 물건은 들어가지 않는다
            res = rec(i + 1, j);
        } else {

            // 넣지 않는 경우와 넣는 경우를 각각 조사
            res = Math.max(rec(i + 1, j), rec(i + 1, j - w[i]) + v[i]);
        }

        return dp[i][j] = res;
    }
}
