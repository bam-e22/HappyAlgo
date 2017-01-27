import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * HappyAlgo#7 Section Scheduling
 */

public class Main {

    public static void main(String[] args) throws IOException {

        int n; // 강의 수
        Lecture[] l;
        int ans = 0;

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        l = new Lecture[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {

            l[i] = new Lecture(0, 0);
            l[i].startTime = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {

            l[i].endTime = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(l);

        int currentTime = l[0].startTime;
        for (int i = 0; i < n; i++) {

            if(currentTime <= l[i].startTime) {

                ans++;
                currentTime = l[i].endTime;
            }
        }

        System.out.println(ans);
    }
}

class Lecture implements Comparable {

    int startTime;
    int endTime;

    Lecture(int s, int t) {

        this.startTime = s;
        this.endTime = t;
    }

    @Override
    public int compareTo(Object o) {

        return this.endTime < ((Lecture) o).endTime ? -1 : this.endTime == ((Lecture) o).endTime ? 0 : 1;
    }

    @Override
    public String toString() {

        return "{s = " + startTime + "| t = " + endTime + "}";
    }
}
