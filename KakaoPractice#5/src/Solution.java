/**
 * 땅따먹기 게임을 하려고 합니다. 땅따먹기 게임의 땅(land)은 총 N행 4열로 이루어져 있고, 모든 칸에는 점수가 쓰여 있습니다.
 * 1행부터 땅을 밟으며 한 행씩 내려올 때, 각 행의 4칸 중 한 칸만 밟으면서 내려와야 합니다.
 * 단, 땅따먹기 게임에는 한 행씩 내려올 때, 같은 열을 연속해서 밟을 수 없는 특수 규칙이 있습니다.
 */
class Solution {

    static int[][] dp = new int[100001][4];

    static int solution(int[][] land) {

        int answer = 0;
        for (int col = 0; col < 4; col++) {

            answer = Math.max(answer, solve(land, 0, col));
        }

        return answer;
    }

    static int solve(int[][] land, int rowIdx, int prevColIdx) {

        // 기저 조건
        if (rowIdx >= land.length) return 0;

        // Memoization
        if (dp[rowIdx][prevColIdx] != 0) return dp[rowIdx][prevColIdx];

        // 탐색
        int maxSum = 0;

        for (int col = 0; col < 4; col++) {

            if (col == prevColIdx) continue;

            maxSum = Math.max(solve(land, rowIdx + 1, col) + land[rowIdx][col], maxSum);
        }

        return dp[rowIdx][prevColIdx] = maxSum;
    }

    static int solveRecursive(int[][] land, int rowIdx, int prevColIdx) {

        // 기저 조건
        if (rowIdx >= land.length) return 0;

        // Memoization
        if (dp[rowIdx][prevColIdx] != 0) return dp[rowIdx][prevColIdx];

        // 탐색
        int maxSum = 0;

        for (int col = 0; col < 4; col++) {

            if (col == prevColIdx) continue;

            maxSum = Math.max(solveRecursive(land, rowIdx + 1, col) + land[rowIdx][col], maxSum);
        }

        return dp[rowIdx][prevColIdx] = maxSum;
    }
}