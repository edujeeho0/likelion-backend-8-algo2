package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


// https://www.acmicpc.net/problem/1912
public class Boj1912 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] numbers = new int[n];
        String[] numberStrings = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(numberStrings[i]);
        }

        // dp[i]에 들어가는 숫자는,
        // dp[i - 1] + numbers[i]와
        // numbers[i]
        // 중 더 큰 숫자가 저장된다.
        int[] dp = new int[n];
        dp[0] = numbers[0];
        // 전체 경우에서 최대를 찾아야 함으로, max는 따로 관리
        int max = numbers[0];
        // i = 1 부터 마지막 숫자까지 고려해가며 dp 배열을 채운다.
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i - 1] + numbers[i], numbers[i]);
            max = Math.max(max, dp[i]);
        }

//        System.out.println(Arrays.toString(dp));
        System.out.println(max);
    }
}
