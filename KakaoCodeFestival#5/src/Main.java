import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws IOException {

        //int[][] data = {{0, 0}, {100, 100}, {0, 200}, {200, 0}};
        //int[][] data = {{0, 0}, {1, 1}};
        //int[][] data = {{0, 0}, {1, 1}, {2, 2}};
        //int[][] data = {{3, 4}, {1, 2}, {1, 0}, {500, 450}};
        //int[][] data = {{5, 2}, {1, 2}, {1, 0}, {500, 450}, {232, 112}, {0, 0}};
        //int[][] data = {{3, 2}, {1, 2}, {1, 0}, {0, 0}};
        int[][] data = {{0, 0}, {1, 1}, {0, 2}, {2, 0}};

        System.out.println(solution(data.length, data));
        System.out.println(solution2(data.length, data));
    }

    static int solution(int n, int[][] data) {

        // 좌표 압축
        ArrayList<Integer> xList = new ArrayList<>();
        ArrayList<Integer> yList = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            xList.add(data[i][0]);
            yList.add(data[i][1]);
        }

        ArrayList<Integer> uniqueXList = new ArrayList<>(new HashSet<>(xList));
        ArrayList<Integer> uniqueYList = new ArrayList<>(new HashSet<>(yList));

        Collections.sort(uniqueXList);
        Collections.sort(uniqueYList);

        // 구간합 배열
        int[][] S = new int[n][n];

        for (int i = 0; i < n; i++) {

            int x = uniqueXList.indexOf(new Integer(data[i][0]));
            int y = uniqueYList.indexOf(new Integer(data[i][1]));

            // 좌표 압축 적용
            data[i][0] = x;
            data[i][1] = y;

            // 구간합 배열 초기값
            S[x][y] = 1;
        }

        // N^2 구간합 구하기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                S[i][j] += (i - 1 >= 0 ? S[i - 1][j] : 0)
                        + (j - 1 >= 0 ? S[i][j - 1] : 0)
                        - (i - 1 >= 0 && j - 1 >= 0 ? S[i - 1][j - 1] : 0);
            }
        }

        int ans = 0;
        // N^2 모든 쐐기 조합에 대하여 검사
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                // 조건#1 검사 : 직사각형이 아닌 경우
                if (data[i][0] == data[j][0] || data[i][1] == data[j][1]) continue;

                // 조건#2 검사 : 내부에 쐐기가 존재하는 경우
                int startX, startY, endX, endY;

                startX = Math.min(data[i][0], data[j][0]);
                startY = Math.min(data[i][1], data[j][1]);
                endX = Math.max(data[i][0], data[j][0]);
                endY = Math.max(data[i][1], data[j][1]);

                startX++;
                startY++;
                endX--;
                endY--;

                int cnt;

                if (startX > endX || startY > endY) {

                    cnt = 0;
                }
                else {

                    cnt = S[endX][endY] - S[endX][startY-1] - S[startX-1][endY] + S[startX-1][startY-1];
                }

                if (cnt == 0) ans++;
            }
        }

        return ans;
    }

    // N^3 풀이 (통과)
    static int solution2(int n, int[][] data) {

        Node[] list = new Node[n];

        for (int i = 0; i < n; i++) {

            list[i] = new Node(data[i][0], data[i][1]);
        }

        Arrays.sort(list);

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {

                // 조건 1 검사 (직사각형)
                if (list[i].x == list[j].x || list[i].y == list[j].y) continue;

                // 조건 2 검사 (중간에 다른 좌표가 있는지)
                boolean check = false;
                for (int k = i + 1; k < j; k++) {

                    if ((list[k].x > list[i].x && list[k].x < list[j].x)
                            && ((list[k].y > list[i].y && list[k].y < list[j].y) || (list[k].y > list[j].y && list[k].y < list[i].y))) {

                        check = true;
                        break;
                    }
                }

                if (!check) cnt++;
            }
        }

        return cnt;
    }
}

class Node implements Comparable<Node> {

    int x, y;

    Node(int x, int y) {

        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Node o) {

        return this.x < o.x ? -1 : this.x > o.x ? 1 : this.y < o.y ? -1 : this.y > o.y ? 1 : 0;
    }
}