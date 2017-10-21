import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    static int N;
    static int[][] map = new int[21][21];
    static boolean[] visited = new boolean[101];
    static int maxLength = -1;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        // 한 카페에서 출발하여 대각선 방향으로 움직이고, 사각형을 그리며 출발 카페로 돌아와야 한다
        // 해당 지역을 벗어나면 안된다
        // 같은 디저트를 먹으면 안된다
        // 하나의 카페에서 디저트를 먹는 것도 안된다
        // 왔던 길을 되돌아 가는 것도 안된다
        // 디저트를 되도록 많이 먹으려고 한다
        for (int t = 1; t <= T; t++) {

            maxLength = -1;

            // Input
            N = Integer.parseInt(br.readLine());

            for (int i = 0; i < N; i++) {

                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {

                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // Solve
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {

                    solve(row, col);
                }
            }

            System.out.println("#" + t + " " + maxLength);

        } // ~test case loop
    } // ~main

    static void solve(int sRow, int sCol) {

        // 탐색 1~4N
        for (int a = 1; a < N; a++) {
            for (int b = 1; b < N; b++) {

                // 맵 범위 체크
                if (sCol + a < N // 오른쪽
                        && sRow + a + b < N // 아래
                        && sCol - b >= 0 // 왼쪽
                        // 탐색할 가치가 있는 경우
                        && maxLength < 2 * (a + b)) {

                    Arrays.fill(visited, false);
                    boolean ret = true;

                    int row = sRow;
                    int col = sCol;

                    // 1. 우하
                    for (int i = 0; i < a; i++) {

                        row++;
                        col++;

                        if (visited[map[row][col]]) {

                            ret = false;
                            break;
                        }

                        visited[map[row][col]] = true;
                    } // ~1. 우하

                    // 2. 좌하
                    if (!ret) continue;
                    for (int i = 0; i < b; i++) {

                        row++;
                        col--;

                        if (visited[map[row][col]]) {

                            ret = false;
                            break;
                        }

                        visited[map[row][col]] = true;
                    } // ~2. 좌하

                    // 3. 좌상
                    if (!ret) continue;
                    for (int i = 0; i < a; i++) {

                        row--;
                        col--;

                        if (visited[map[row][col]]) {

                            ret = false;
                            break;
                        }

                        visited[map[row][col]] = true;
                    } // ~3. 좌상

                    // 4. 우상
                    if (!ret) continue;
                    for (int i = 0; i < b; i++) {

                        row--;
                        col++;

                        if (visited[map[row][col]]) {

                            ret = false;
                            break;
                        }

                        visited[map[row][col]] = true;
                    } // ~4. 우상

                    if (!ret) continue;
                    maxLength = 2 * (a + b);
                }
            }
        }

    } // ~solve

}

