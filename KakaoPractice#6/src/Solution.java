/**
 * N개의 스티커가 원형으로 연결되어 있습니다. 다음 그림은 N = 8인 경우의 예시입니다.
 * 원형으로 연결된 스티커에서 몇 장의 스티커를 뜯어내어 뜯어낸 스티커에 적힌 숫자의 합이 최대가 되도록 하고 싶습니다.
 * 단 스티커 한 장을 뜯어내면 양쪽으로 인접해있는 스티커는 찢어져서 사용할 수 없게 됩니다.
 */
class Solution {

    static int[][] dp = new int[100001][2];

    public static int solution(int sticker[]) {

        int N = sticker.length;

        if (N == 1) return sticker[0];
        if (N == 2) return Math.max(sticker[0], sticker[1]);

        dp[0][0] = sticker[0];
        dp[1][0] = dp[0][0];

        dp[0][1] = 0;
        dp[1][1] = sticker[1];

        for (int i = 2; i < N - 1; i++) {

            dp[i][0] = Math.max(dp[i - 2][0] + sticker[i], dp[i - 1][0]);
        }
        dp[N - 1][0] = dp[N - 2][0];

        for (int i = 2; i < N; i++) {

            dp[i][1] = Math.max(dp[i - 2][1] + sticker[i], dp[i - 1][1]);
        }

        return Math.max(dp[N - 1][0], dp[N - 1][1]);
    }
}