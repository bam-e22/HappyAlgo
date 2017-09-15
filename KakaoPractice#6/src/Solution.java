/**
 * N개의 스티커가 원형으로 연결되어 있습니다. 다음 그림은 N = 8인 경우의 예시입니다.
 * 원형으로 연결된 스티커에서 몇 장의 스티커를 뜯어내어 뜯어낸 스티커에 적힌 숫자의 합이 최대가 되도록 하고 싶습니다.
 * 단 스티커 한 장을 뜯어내면 양쪽으로 인접해있는 스티커는 찢어져서 사용할 수 없게 됩니다.
 */
class Solution {

    static int[][] dp = new int[100001][2];

    public static int solution(int sticker[]) {

        int N = sticker.length;

        // N <= 2 인 경우
        if (N == 1) return sticker[0];
        if (N == 2) return Math.max(sticker[0], sticker[1]);

        // N > 2 인 경우

        // 해설 : 0번 스티커를 기준으로 했을 때, 모든 경우의 수는 0번 스티커를 뜯는/뜯지 않는 경우, 이 2가지 경우로 나눌 수 있다

        // dp 정의 : dp[N] -> N번째 스티커까지 진행했을 때 얻을 수 있는 최대 값
        // dp[N][0] -> N번째 스티커까지 진행했을 때 얻을 수 있는 최대 값(0번째 스티커를 뜯는 경우)
        // dp[N][1] -> N번째 스티커까지 진행했을 때 얻을 수 있는 최대 값(0번째 스티커를 뜯지 않는 경우)

        // 1. 0번 스티커를 뜯는 경우
        dp[0][0] = sticker[0];
        dp[1][0] = dp[0][0];

        for (int i = 2; i < N - 1; i++) {

            // i 번째에서 얻을 수 있는 최대값은 2가지 중 max값이다
            // 1) i-2번째에서 얻을 수 있는 최대값 + 현재 스티커 값
            // 2) i-1번째에서 얻을 수 있는 최대값
            dp[i][0] = Math.max(dp[i - 2][0] + sticker[i], dp[i - 1][0]);
        }
        dp[N - 1][0] = dp[N - 2][0];

        // 2. 0번 스티커를 뜯지 않는 경우
        dp[0][1] = 0;
        dp[1][1] = sticker[1];

        for (int i = 2; i < N; i++) {

            dp[i][1] = Math.max(dp[i - 2][1] + sticker[i], dp[i - 1][1]);
        }


        // Result
        return Math.max(dp[N - 1][0], dp[N - 1][1]);
    }
}