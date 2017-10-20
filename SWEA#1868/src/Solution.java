import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 1) 지뢰를 클릭한 경우
 * 표의 각 칸을 클릭했을 때, 그 칸이 지뢰가 있는 칸이라면 ‘파핑 파핑!’이라는 소리와 함께 게임은 끝난다.
 * 2) 빈칸을 클릭한 경우
 * 지뢰가 없는 칸이라면 변이 맞닿아 있거나 꼭지점이 맞닿아 있는 최대 8칸에 대해 몇 개의 지뢰가 있는지가 0에서 8사이의 숫자로 클릭한 칸에 표시된다.
 * 만약 이 숫자가 0이라면 근처의 8방향에 지뢰가 없다는 것이 확정된 것이기 때문에 그 8방향의 칸도 자동으로 숫자를 표시해 준다.
 *
 * 실제 게임에서는 어떤 위치에 지뢰가 있는지 알 수 없지만, 이 문제에서는 특별히 알 수 있다고 하자.
 *
 */

public class Solution {

    static final int BLANK = -1;
    static final int MINE = -2;

    static final int[] dRow = { -1, -1, -1, 0, 0, 1, 1, 1 };
    static final int[] dCol = { -1, 0, 1, -1, 1, -1, 0, 1 };

    static int N;
    static Queue<Node> queue = new LinkedList<>();
    static int[][] map = new int[301][301];
    static int[][] copyMap = new int[301][301];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {

            // input
            N = Integer.parseInt(br.readLine());
            queue.clear();

            for (int i = 0; i < N; i++) {

                String input = br.readLine();
                for (int j = 0; j < N; j++) {

                    char c = input.charAt(j);

                    if (c == '.') {

                        map[i][j] = BLANK;
                        copyMap[i][j] = BLANK;
                    } else {

                        map[i][j] = MINE;
                        copyMap[i][j] = MINE;
                    }
                }
            }

            // solve
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {

                    if (map[i][j] == BLANK) {

                        checkMine(map, i, j);
                    }
                }
            }

            int step = 0;
            while (!queue.isEmpty()) {

                Node u = queue.poll();

                if (copyMap[u.row][u.col] == BLANK) {

                    step++;
                    checkMine(copyMap, u.row, u.col);
                }
            }

            // count remain cell
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {

                    if (copyMap[i][j] == BLANK) {

                        step++;
                    }
                }
            }

            System.out.println("#" + t + " " + step);

        } // ~test case loop

    } // ~main

    static int checkMine(int[][] map, int row, int col) {

        int cnt = 0;
        for (int i = 0; i < 8; i++) {

            int nextRow = row + dRow[i];
            int nextCol = col + dCol[i];

            if (nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= N) continue;

            if (map[nextRow][nextCol] == MINE) cnt++;
        }

        map[row][col] = cnt;

        if (cnt == 0) {

            queue.add(new Node(row, col));

            for (int i = 0; i < 8; i++) {

                int nextRow = row + dRow[i];
                int nextCol = col + dCol[i];

                if (nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= N) continue;

                if (map[nextRow][nextCol] == BLANK) {

                    checkMine(map, nextRow, nextCol);
                }
            }
        }

        return cnt;
    }
}

class Node {

    int row, col;

    Node(int row, int col) {

        this.row = row;
        this.col = col;
    }
}

