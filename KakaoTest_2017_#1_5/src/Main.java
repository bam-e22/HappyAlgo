import java.util.HashMap;
import java.util.Iterator;

/**
 * 카카오 Test#1_5 뉴스 클러스터링
 */
public class Main {

    static final int MUL = 65536;

    public static void main(String[] args) {

        String str1 = "aa1+aa2";
        // String str1 = "AABBCCBB";
        String str2 = "AAAA12";

        System.out.println(solution(str1.toLowerCase(), str2.toLowerCase()));
    }

    static int solution(String str1, String str2) {

        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();

        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();
        HashMap<String, Integer> minMap = new HashMap<>();
        HashMap<String, Integer> maxMap = new HashMap<>();

        // 다중집합1
        for (int i = 0; i < str1.length() - 1; i++) {

            char c1 = str1.charAt(i);
            char c2 = str1.charAt(i + 1);

            if (c1 >= 65 && c1 <= 90 && c2 >= 65 && c2 <= 90) {

                String subString = str1.substring(i, i + 2);

                if (map1.containsKey(subString)) map1.put(subString, map1.get(subString) + 1);
                else map1.put(subString, 1);
            }
        }

        // 다중집합2
        for (int i = 0; i < str2.length() - 1; i++) {

            char c1 = str2.charAt(i);
            char c2 = str2.charAt(i + 1);

            if (c1 >= 65 && c1 <= 90 && c2 >= 65 && c2 <= 90) {

                String subString = str2.substring(i, i + 2);

                if (map2.containsKey(subString)) map2.put(subString, map2.get(subString) + 1);
                else map2.put(subString, 1);
            }
        }

        // 교집합, 합집합
        for (String key : map1.keySet()) {

            if (minMap.containsKey(key)) {

                if (minMap.get(key) > map1.get(key)) {

                    minMap.put(key, map1.get(key));
                }
            } else {

                minMap.put(key, map1.get(key));
            }

            if (maxMap.containsKey(key)) {

                if (maxMap.get(key) < map1.get(key)) {

                    maxMap.put(key, map1.get(key));
                }
            } else {

                maxMap.put(key, map1.get(key));
            }
        }

        for (String key : map2.keySet()) {

            if (minMap.containsKey(key)) {

                if (minMap.get(key) > map2.get(key)) {

                    minMap.put(key, map2.get(key));
                }
            }

            if (maxMap.containsKey(key)) {

                if (maxMap.get(key) < map2.get(key)) {

                    maxMap.put(key, map2.get(key));
                }
            } else {

                maxMap.put(key, map2.get(key));
            }
        }

        for (String key : minMap.keySet()) {

            if (!map2.containsKey(key)) minMap.remove(key);
        }

        Iterator<String> min = minMap.keySet().iterator();
        Iterator<String> max = maxMap.keySet().iterator();

        int minSize = 0;
        int maxSize = 0;

        while (min.hasNext()) minSize += minMap.get(min.next());
        while (max.hasNext()) maxSize += maxMap.get(max.next());

        int answer;

        if (minSize == 0 && maxSize == 0) answer = MUL;
        else answer = (int) (((double) minSize / (double) maxSize) * MUL);

        return answer;
    }
}

