import java.util.Arrays;
import java.util.HashSet;

public class Solution {

    static HashSet<String> set = new HashSet<>();
    static int[] dp = new int[20001];

    static final int INF = 1000000000;

    static {

        Arrays.fill(dp, -1);
    }

    public static int solution(String[] strs, String t) {

        for (String s : strs) {

            set.add(s);
        }

        solveRecursive(t, 0);

        return dp[0] == INF ? -1 : dp[0];
    }

    public static int solveRecursive(String t, int idx) {

        // 기저 조건
        if (idx == t.length()) return 0;

        // memoization
        if (dp[idx] != -1) return dp[idx];

        dp[idx] = INF;

        // 탐색
        for (int i = idx; i < Math.min(idx + 5, t.length()); i++) {

            String s = t.substring(idx, i + 1);

            if (set.contains(s)) {

                dp[idx] = Math.min(dp[idx], solveRecursive(t, idx + s.length()) + 1);
            }
        }

        return dp[idx];
    }
}
