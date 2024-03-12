package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj11660 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] info = reader.readLine().split(" ");
        int size = Integer.parseInt(info[0]);
        int points = Integer.parseInt(info[1]);

        int[][] board = new int[size + 1][size + 1];
        for (int i = 1; i < size + 1; i++) {
            String[] row = reader.readLine().split(" ");
            for (int j = 1; j < size + 1; j++) {
                board[i][j] = Integer.parseInt(row[j - 1]);
            }
        }

        // dp[y][x]에는 (0, 0)에서 (y, x)까지 모든 값을 합한 값을 저장해둔다.
        int[][] dp = new int[size + 1][size + 1];
        for (int i = 1; i < size + 1; i++) {
            for (int j = 1; j < size + 1; j++) {
                // 직전 행, 열까지의 합을 더하고, 겹치는 부분을 한번 빼준 다음 [y][x]의 값을 더해주면
                // 그 지점까지의 누적합이 된다.
                // 직전 행, 열이 0(없으면) 일때를 대비해 0 ~ n 까지 index를 배치,
                // y또는 x가 0이면 0을 할당해준다.
                dp[i][j] = board[i][j] + dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];
            }
        }

        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < points; i++) {
            String[] pointInfo = reader.readLine().split(" ");
            int y1 = Integer.parseInt(pointInfo[0]);
            int x1 = Integer.parseInt(pointInfo[1]);
            int y2 = Integer.parseInt(pointInfo[2]);
            int x2 = Integer.parseInt(pointInfo[3]);
            // 각 점을 기준으로, y2 * x2 - y2 * x1 - y1 * x2 + y1 * x1
            int sum = dp[y2][x2] - dp[y2][x1 - 1] - dp[y1 - 1][x2] + dp[y1 - 1][x1 - 1];
            answer.append(sum).append('\n');
        }
        System.out.print(answer);
    }
}
