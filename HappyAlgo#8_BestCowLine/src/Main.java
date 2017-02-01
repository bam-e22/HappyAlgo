import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * POJ#3617 Best Cow Line
 * http://poj.org/problem?id=3617
 */
public class Main {

    public static void main(String[] args) throws IOException {

        int N; // cows
        StringBuffer S = new StringBuffer();
        StringBuffer T = new StringBuffer();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {

            S.append(br.readLine());
        }

        int a = 0;
        int b = N - 1;
        int num = 0;
        boolean left;

        while (a <= b) {


            left = false;

            for (int i = 0; a + i <= b; i++) {

                if (S.charAt(a + i) < S.charAt(b - i)) {

                    left = true;
                    break;
                } else if (S.charAt(a + i) > S.charAt(b - i)) {

                    left = false;
                    break;
                }
            }

            if (left) {

                T.append(S.charAt(a++));
            } else {

                T.append(S.charAt(b--));
            }

            if (++num % 80 == 0) {

                T.append("\n");
            }

        }

        System.out.println(T);
    }
}
