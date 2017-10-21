import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * SWEA#1949 등산로 조성
 */
public class Solution {

    static int N, K;
    static int[][] map = new int[9][9];
    static boolean[][] visited = new boolean[9][9];
    static int maxHeight = 0;
    static int maxLength = 0;
    static ArrayList<Node> startPos = new ArrayList<>();

    static final int[] dRow = { 0, -1, 0, 1 };
    static final int[] dCol = { -1, 0, 1, 0 };

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {

            maxHeight = 0;
            maxLength = 0;
            startPos.clear();
            initArr();

            // Input
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            for (int i = 0; i < N; i++) {

                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {

                    map[i][j] = Integer.parseInt(st.nextToken());
                    maxHeight = maxHeight < map[i][j] ? map[i][j] : maxHeight;
                }
            }

            // Solve
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {

                    if (map[row][col] == maxHeight) {

                        int length = dfs(row, col, 1, 0);
                        maxLength = maxLength < length ? length : maxLength;
                    }
                }
            }

            System.out.println("#" + t + " " + maxLength);

        } // ~test case loop
    } // ~main

    // 길이
    static int dfs(int row, int col, int k, int depth) {

        visited[row][col] = true;

        // 탐색
        int length = 1;

        for (int i = 0; i < 4; i++) {

            int nextRow = row + dRow[i];
            int nextCol = col + dCol[i];

            if (nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= N) continue;
            if (visited[nextRow][nextCol]) continue;

            // 공사를 해야 하고,
            if (map[nextRow][nextCol] >= map[row][col]) {

                int diff = map[nextRow][nextCol] - map[row][col] + 1;

                // 공사를 할 수 있는 경우
                if (k == 1 && K >= diff) {

                    map[nextRow][nextCol] -= diff;
                    length = Math.max(length, dfs(nextRow, nextCol, 0, depth + 1) + 1);
                    map[nextRow][nextCol] += diff;
                }
            } else {

                length = Math.max(length, dfs(nextRow, nextCol, k, depth + 1) + 1);
            }
        }

        visited[row][col] = false;

        return length;
    }

    static void printMap() {

        for (int i = 0; i < N; i++) {

            for (int j = 0; j < N; j++) {

                System.out.printf("%4d", map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    static void initArr() {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                visited[i][j] = false;
            }
        }
    }
}

class Node {

    int row, col;

    Node(int row, int col) {

        this.row = row;
        this.col = col;
    }
}

