import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * HappyAlgo#10 Expedition
 * POJ#2431
 * http://poj.org/problem?id=2431
 */

public class Main {

    public static void main(String[] args) throws IOException {

        int N; // 가솔린 스탠드
        int L; // 이동 거리
        int P; // 가솔린
        int[] A; // 시작 지점부터 거리 Ai의 가솔린 스탠드
        int[] B; // Ai에서 확보할 수 있는 가솔린 Bi

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        A = new int[N];
        B = new int[N];

        Queue pq = new PriorityQueue<FuelStop>();

        for (int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        // solve
        System.out.println(solve(L, P, N, A, B));

    }

    static int solve(int L, int P, int N, int[] A, int[] B) {

        int count = 0;
        Queue pq = new PriorityQueue<Integer>();

        for (int i = N - 1; i >= 0; i--) {

            // 다음으로 이동할 거리

            int d = L - A[i];

            while (P - d < 0) {

                if (pq.isEmpty()) {

                    return -1;
                }

                P += (Integer) pq.poll();
                count++;
            }

            P -= d;
            L -= d;
            pq.add(B[i]);

        }

        return count;
    }

} // Main

class FuelStop implements Comparable {

    int distance;
    int fuel;

    FuelStop(int d, int f) {

        this.distance = d;
        this.fuel = f;
    }

    @Override
    public int compareTo(Object o) {

        //return ((FuelStop) o).fuel > this.fuel ? 1 : -1;
        return ((FuelStop) o).distance > this.distance ? 1 : -1;
    }

    @Override
    public String toString() {

        return "d = " + distance + ", f = " + fuel;
    }
} // FuelStop