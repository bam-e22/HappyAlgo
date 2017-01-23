import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Lake Counting (POJ No.2386)
 * http://poj.org/problem?id=2386
 */

public class Main {

    static final char WATER = 'W';
    static final char DRYLAND = '.';
    static int count = 0;
    static char[][] map = new char[100][100];

    public static void main(String[] args) throws IOException {

        int N;
        int M;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // input
        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine());
            String row = st.nextToken();

            for (int j = 0; j < M; j++) {

                map[i][j] = row.charAt(j);
            }
        }

        // dfs
        for (int i = 0; i < N; i++) {

            for (int j = 0; j < M; j++) {

                if (map[i][j] == WATER) {

                    dfs(N, M, i, j);
                    count++;
                }
            }
        }

        System.out.println(count);
    } // main

    static void dfs(int N, int M, int x, int y) {

        // 현재 위치를 .으로 치환
        map[x][y] = DRYLAND;

        // 8 방향 탐색
        for (int dx = -1; dx <= 1; dx++) {

            for (int dy = -1; dy <= 1; dy++) {

                int nx = x + dx;
                int ny = y + dy;

                // check boundary
                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {

                    // check water
                    if (map[nx][ny] == WATER) {

                        dfs(N, M, nx, ny);
                    }
                }
            }
        }
    } // dfs
}
