package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj9465 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int testCases = Integer.parseInt(reader.readLine());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < testCases; i++) {
            int n = Integer.parseInt(reader.readLine());
            int[][] stickers = new int[2][n];
            for (int j = 0; j < 2; j++) {
                StringTokenizer stickerToken = new StringTokenizer(reader.readLine());
                for (int k = 0; k < n; k++) {
                    stickers[j][k] = Integer.parseInt(stickerToken.nextToken());
                }
            }
            answer.append(solve(n, stickers)).append('\n');
        }
        System.out.print(answer);
    }

    private static int solve(int cols, int[][] stickers) {
        if (cols == 1) return Math.max(stickers[0][0], stickers[1][0]);
        int[][] table = new int[2][cols];
        // 왼쪽끝을 첫 칸으로 생각하면,
        // 첫번째 칸의 최댓값은 각각 스티커의 가치,
        table[0][0] = stickers[0][0];
        table[1][0] = stickers[1][0];
        // 두번째 칸의 최댓값은 이전칸의 대각선 스티커 + 이번 스티커
        table[0][1] = table[1][0] + stickers[0][1];
        table[1][1] = table[0][0] + stickers[1][1];
        for (int i = 2; i < cols; i++) {
            // 세번째 칸 이후로는 직전칸 대각선 스티커의 가치 vs 두칸 전 대각선 스티커의 가치이다.
            // 바로 옆의 스티커는 사용할 수 없으며, 두칸 전 같은 칸의 스티커를 고려한 경우는 직전칸 대각선에 최댓값이 있기 때문.
            table[0][i] = Math.max(table[1][i - 1] + stickers[0][i], table[1][i - 2] + stickers[0][i]);
            table[1][i] = Math.max(table[0][i - 1] + stickers[1][i], table[0][i - 2] + stickers[1][i]);
        }

        // 마지막 칸에 담기는 값 중 더 큰게 더 정답이다.
        return Math.max(table[0][cols - 1], table[1][cols - 1]);
    }
}
