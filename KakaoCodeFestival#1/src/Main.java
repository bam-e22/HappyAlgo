import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 카카오 프렌즈 컬러링북
 * 출판사의 편집자인 어피치는 네오에게 컬러링북에 들어갈 원화를 그려달라고 부탁하여 여러 장의 그림을 받았다.
 * 여러 장의 그림을 난이도 순으로 컬러링북에 넣고 싶었던 어피치는
 * 영역이 많으면 색칠하기가 까다로워 어려워진다는 사실을 발견하고 그림의 난이도를 영역의 수로 정의하였다.
 * (영역이란 상하좌우로 연결된 같은 색상의 공간을 의미한다.)
 * 그림에 몇 개의 영역이 있는지와 가장 큰 영역의 넓이는 얼마인지 계산하는 프로그램을 작성해보자.
 */
public class Main {

    static final int[] dRow = {0, -1, 0, 1};
    static final int[] dCol = {-1, 0, 1, 0};

    public static void main(String[] args) {

        int m = 6;
        int n = 4;

        int[][] picture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};

        System.out.println(Arrays.toString(solution(m, n, picture)));


    }

    public static int[] solution(int m, int n, int[][] picture) {

        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        boolean[][] discovered = new boolean[m][n];

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {

                if (!discovered[row][col] && picture[row][col] != 0) {

                    int sizeOfArea = bfs(row, col, m, n, picture, discovered);
                    numberOfArea++;
                    maxSizeOfOneArea = maxSizeOfOneArea < sizeOfArea ? sizeOfArea : maxSizeOfOneArea;
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    static int bfs(int row, int col, int m, int n, int[][] picture, boolean[][] discovered) {

        int color = picture[row][col];

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(row, col));

        discovered[row][col] = true;

        int num = 0;

        while (!queue.isEmpty()) {

            Node u = queue.poll();
            num++;

            for (int i = 0; i < 4; i++) {

                int nextRow = u.row + dRow[i];
                int nextCol = u.col + dCol[i];

                if (nextRow < 0 || nextCol < 0 || nextRow >= m || nextCol >= n) continue;
                if (picture[nextRow][nextCol] != color) continue;
                if (discovered[nextRow][nextCol]) continue;

                queue.add(new Node(nextRow, nextCol));
                discovered[nextRow][nextCol] = true;
            }
        }

        return num;
    }
}

class Node {

    int row, col;

    Node(int row, int col) {

        this.row = row;
        this.col = col;
    }
}
