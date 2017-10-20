import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

    static int N;
    static ArrayList<Node> pos = new ArrayList<>();
    static int[][] map = new int[13][13];
    static int nPoweredProcessor;
    static int minLength;

    static final int BLANK = 0;
    static final int PROCESSOR = 1;
    static final int CONNECT = 2;

    static final int WEST = 0;
    static final int NORTH = 1;
    static final int EAST = 2;
    static final int SOUTH = 3;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {

            // Input
            N = Integer.parseInt(br.readLine());

            nPoweredProcessor = 0;
            minLength = Integer.MAX_VALUE;
            pos.clear();

            for (int row = 0; row < N; row++) {

                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int col = 0; col < N; col++) {

                    map[row][col] = Integer.parseInt(st.nextToken());

                    // 이미 전원 연결된 프로세서
                    if (row == 0 || col == 0 || row == N - 1 || col == N - 1) {

                        nPoweredProcessor++;
                    }
                    // 전원 연결이 필요한 프로세서
                    else {

                        if (map[row][col] == PROCESSOR) pos.add(new Node(row, col));
                    }
                }
            }

            // Solve

            backtracking(0, nPoweredProcessor, 0);

            System.out.println("#" + t + " " + (minLength == Integer.MAX_VALUE ? 0 : minLength));

        } // ~test case loop
    } // ~main

    static void backtracking(int idx, int nProcessor, int length) {

        // 완료
        if (idx == pos.size()) {

            // 최대 연결 프로세서 갱신
            if (nPoweredProcessor < nProcessor) {

                nPoweredProcessor = nProcessor;
                minLength = length;

            } else if (nPoweredProcessor == nProcessor) {

                minLength = minLength > length ? length : minLength;
            }

            return;
        }

        int row = pos.get(idx).row;
        int col = pos.get(idx).col;

        // 탐색
        boolean ret = false;
        for (int dir = 0; dir < 4; dir++) {

            if (isSafe(row, col, dir)) {

                ret = true;
                int addedLength = connect(row, col, dir, CONNECT);
                backtracking(idx + 1, nProcessor + 1, length + addedLength);
                connect(row, col, dir, BLANK);
            }
        }

        if (!ret) {

            backtracking(idx + 1, nProcessor, length);
        }
    }

    static boolean isSafe(int targetRow, int targetCol, int dir) {

        boolean ret = true;

        switch (dir) {

            case WEST:

                for (int col = targetCol - 1; col >= 0; col--) {

                    if (map[targetRow][col] != BLANK) ret = false;
                }

                break;
            case NORTH:

                for (int row = targetRow - 1; row >= 0; row--) {

                    if (map[row][targetCol] != BLANK) ret = false;
                }

                break;
            case EAST:

                for (int col = targetCol + 1; col <= N - 1; col++) {

                    if (map[targetRow][col] != BLANK) ret = false;
                }

                break;
            case SOUTH:

                for (int row = targetRow + 1; row <= N - 1; row++) {

                    if (map[row][targetCol] != BLANK) ret = false;
                }

                break;
        }

        return ret;
    }

    static int connect(int targetRow, int targetCol, int dir, int value) {

        int addedLength = 0;

        switch (dir) {

            case WEST:

                for (int col = targetCol - 1; col >= 0; col--) {

                    map[targetRow][col] = value;
                    addedLength++;
                }

                break;
            case NORTH:

                for (int row = targetRow - 1; row >= 0; row--) {

                    map[row][targetCol] = value;
                    addedLength++;
                }

                break;
            case EAST:

                for (int col = targetCol + 1; col <= N - 1; col++) {

                    map[targetRow][col] = value;
                    addedLength++;
                }

                break;
            case SOUTH:

                for (int row = targetRow + 1; row <= N - 1; row++) {

                    map[row][targetCol] = value;
                    addedLength++;
                }

                break;
        }

        return addedLength++;
    }

    static void printMap() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

class Node {

    int row, col;

    Node(int row, int col) {

        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {

        return "(" + row + ", " + col + ")";
    }
}