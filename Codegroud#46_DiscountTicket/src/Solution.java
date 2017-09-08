import java.util.Arrays;
import java.util.Scanner;

class Solution {

    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);

        int T;
        int test_case;

        T = sc.nextInt();
        for (test_case = 1; test_case <= T; test_case++) {

            int INF = 100000000;

            int N; // 정거장의 수
            int M; // 간선의 수
            int K; // 할인권 비용
            int[][] adj; // 인접행렬

            N = sc.nextInt();
            M = sc.nextInt();
            K = sc.nextInt();

            adj = new int[N + 1][N + 1];

            for (int i = 0; i < N + 1; i++) {

                Arrays.fill(adj[i], INF);
            }

            for (int i = 0; i < M; i++) {

                int a = sc.nextInt();
                int b = sc.nextInt();
                int c = sc.nextInt();

                adj[a][b] = c;
                adj[b][a] = c;
            }

            // 플로이드-와셜
            for (int i = 1; i < N + 1; i++) {

                for (int j = 1; j < N + 1; j++) {

                    for (int k = 1; k < N + 1; k++) {

                        adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
                    }
                }
            }

            int P = sc.nextInt();
            int cnt = 0;

            for (int i = 0; i < P; i++) {

                int S = sc.nextInt();
                int E = sc.nextInt();

                if (K < adj[S][E]) {

                    cnt++;
                }
            }

            System.out.println("Case #" + test_case);
            System.out.println(cnt);

        }
    }
}