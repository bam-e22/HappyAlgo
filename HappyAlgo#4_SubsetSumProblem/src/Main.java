import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        int n;
        int k;
        int[] a;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        a = new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {

            a[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(dfs(n, a, k, 0, 0) ? "YES" : "NO");

    }

    static boolean dfs(int n, int[] a, int k, int sum, int index) {

        // Stop
        if (index >= n) {

            return sum == k;
        }

        // 더하지 않는 경우
        if (dfs(n, a, k, sum, index + 1)) {

            return true;
        }

        // 더하는 경우
        if (dfs(n, a, k, sum + a[index], index + 1)) {

            return true;
        }

        return false;
    }
}

