import java.util.PriorityQueue;

/**
 * 카카오 Test#1_4 셔틀버스
 */
public class Main {

    public static void main(String[] args) {

        String[] timeTable = {"08:00", "08:01", "08:02", "08:03"};
        System.out.println(solution(1, 1, 5, timeTable));

        String[] timeTable2 = {"09:10", "09:09", "08:00"};
        System.out.println(solution(2, 10, 2, timeTable2));

        String[] timeTable3 = {"09:00", "09:00", "09:00", "09:00"};
        System.out.println(solution(2, 1, 2, timeTable3));

        String[] timeTable4 = {"00:01", "00:01", "00:01", "00:01", "00:01"};
        System.out.println(solution(1, 1, 5, timeTable4));

        String[] timeTable5 = {"23:59"};
        System.out.println(solution(1, 1, 1, timeTable5));

        String[] timeTable6 = {"23:59", "23:59", "23:59", "23:59", "23:59", "23:59",
                "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"};
        System.out.println(solution(10, 60, 45, timeTable6));

        String[] timeTable7 = {"00:01", "00:00", "00:01"};
        System.out.println(solution(1, 1, 3, timeTable7));
    }

    /**
     * 1. con은 출근을 하긴 해야한다
     * 2. con은 가장 늦게 버스정류장에 도착하고 싶다
     * 3. 즉, 마지막 버스(막차)를 타면 되는데, 몇시 몇분에 줄을 서야 하는지 알아내보자
     * 4. 모든 crew의 출근이 보장되는 것은 아니다. 그러므로 처음부터 순서대로 시뮬레이션을 해보면서 가장 늦게 도착할 수 있는 시간을 구해보자
     * 5-1. 버스의 정원이 남는 경우, con은 버스 도착 시간에 맞춰 정류장에 도착하면 된다
     * 5-2. 버스의 정원이 남지 않는 경우, con은 마지막에 탑승한 crew보다 1분 일찍 정류장에 도착하면 된다
     */
    static String solution(int n, int t, int m, String[] timetable) {

        PriorityQueue<Time> pq = new PriorityQueue<>();
        for (String time : timetable) pq.add(new Time(time));

        // conTime : 콘이 버스 정류장에 가장 늦게 도착할 수 있는 시간
        Time conTime = new Time("00:00");

        for (int i = 0; i < n; i++) {

            Time busTime = new Time("09:00");
            busTime.add(i, t);

            conTime = busTime;

            int capacity = m;

            // 버스에 사람 태우기
            while (!pq.isEmpty() && capacity > 0 && pq.peek().compareTo(busTime) <= 0) {

                Time crewTime = pq.poll();

                if (capacity == 1) {

                    conTime = crewTime;
                    conTime.add(1, -1);
                }

                capacity--;
            }
        }

        return conTime.toString();
    }
}

class Time implements Comparable<Time> {

    int h, m;

    Time(String time) {

        this.h = Integer.parseInt(time.substring(0, 2));
        this.m = Integer.parseInt(time.substring(3, 5));
    }

    void add(int n, int t) {

        int addHour = (n * t) / 60;
        int addMin = (n * t) % 60;

        h += addHour;
        m += addMin;

        if (m >= 60) {

            h++;
            m %= 60;
        } else if (m < 0) {

            h--;
            m = 60 + m;
        }
    }

    @Override
    public String toString() {

        return String.format("%02d:%02d", this.h, this.m);
    }

    @Override
    public int compareTo(Time o) {

        return this.h < o.h ? -1 : this.h > o.h ? 1 : this.m < o.m ? -1 : this.m > o.m ? 1 : 0;
    }
}
