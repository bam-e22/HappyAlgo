/**
 * 1와 0로 채워진 표(board)가 있습니다. 표 1칸은 1 x 1 의 정사각형으로 이루어져 있습니다.
 * 표에서 1로 이루어진 가장 큰 정사각형을 찾아 넓이를 return 하는 solution 함수를 완성해 주세요.
 * (단, 정사각형이란 축에 평행한 정사각형을 말합니다.)
 */
class Solution {

    public int solution(int[][] board) {

        int answer = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                if (board[i][j] == 0) continue;

                if ((i - 1 >= 0 && board[i - 1][j] > 0)
                        && (j - 1 >= 0 && board[i][j - 1] > 0)
                        && (i - 1 >= 0 && j - 1 >= 0 && board[i - 1][j - 1] > 0)) {

                    board[i][j] = Math.min(Math.min(board[i - 1][j], board[i][j - 1]), board[i - 1][j - 1]) + 1;
                }

                answer = answer < board[i][j] ? board[i][j] : answer;
            }
        }
        return answer * answer;
    }
}