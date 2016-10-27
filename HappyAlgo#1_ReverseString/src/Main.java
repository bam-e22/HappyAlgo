import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 누워서 읽는 알고리즘#1
 * 입력된 String 문자열을 뒤집에서 출력하기
 * + StringBuffer 클래스의 reverse() 함수 이용
 */
public class Main {

	public static void main(String[] args) {

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));) {

			String str = br.readLine();

			StringBuffer sb = new StringBuffer(str);
			sb.reverse();

			System.out.println(sb.toString());

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
