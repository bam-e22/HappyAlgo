/**
 * 카카오 Test#1_1 비밀지도
 */
public class Main {

    public static void main(String[] args) {

        int n = 5;
        int[] arr1 = {9, 20, 28, 18, 11};
        int[] arr2 = {30, 1, 21, 17, 28};

        String[] map = solution(n, arr1, arr2);

        for (int i = 0; i < n; i++) {

            System.out.println(map[i]);
        }
    }

    static String[] solution(int n, int[] arr1, int[] arr2) {

        String[] answer = new String[n];

        for (int i = 0; i < n; i++) {

            answer[i] = convert(arr1[i] | arr2[i]);
        }

        return answer;
    }

    static String convert(int num) {

        StringBuilder sb = new StringBuilder();
        char[] binaryStr = Integer.toBinaryString(num).toCharArray();

        for (char c : binaryStr) {

            if (c == '1') sb.append("#");
            else if (c == '0') sb.append(" ");
        }

        return sb.toString();
    }
}
