package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj2342 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] commandAll = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        // 전체 명령의 갯수
        int count = commandAll.length - 1;
        if (count == 0) {
            System.out.println(0);
            return;
        }

        // 연속적인 명령은 1만 소모하니, 축약한 다음
        // 전체 명령 갯수 - 중복되지 않은 명령을 마지막에 더해준다.
        int[] commandSquashed = new int[count];
        commandSquashed[0] = commandAll[0];
        int last = commandSquashed[0];
        int idx = 1;
        int nonConCount = 1;
        for (int i = 1; i < count; i++) {
            if (commandAll[i] == last) continue;
            last = commandAll[i];
            commandSquashed[idx] = last;
            idx++;
            nonConCount++;
        }
        /*System.out.println(nonConCount);
        System.out.println(Arrays.toString(commandSquashed));*/

        // 2차원 배열
        // 왼발 오른발 구분 없이 발의 위치의 조합으로 생각하면
        // 4 * 100000 공간으로 충분
        // 발의 조합은 깊이가 깊어져도 4가지 밖에 안나온다.
        // 이번에 등장한 명령이 n이라면, 가능한 조합은
        // (0, n), (1, n), (2, n), (3, n), (4, n) 중 n이 중복되지 않는 조합
        int[][] moves = new int[nonConCount][5];
        for (int[] moveRec : moves)
            Arrays.fill(moveRec, Integer.MAX_VALUE);

        moves[0][0] = 2;

        for (int thisTurn = 1; thisTurn < nonConCount; thisTurn++) {
            int lastTurn = thisTurn - 1;
            int thisCommand = commandSquashed[thisTurn];
            int lastCommand = commandSquashed[lastTurn];
            int thisEven = thisCommand % 2;
            // 이번 발의 조합을 만든다. j가 앞쪽 숫자.
            for (int j = 0; j < 5; j++) {
                // 발이 모여있는 경우는 없다.
                if (j == thisCommand) continue;
                // 이전 명령과 동일하면 전부 다 비교해야 한다.
                if (j == lastCommand) {
                    // 0번 발을 움직인 경우가 하한선.
                    int min = moves[lastTurn][0] + 2;
                    for (int k = 1; k < 5; k++) {
                        // 이전 명령의 결과로 발이 모였다는 뜻이니 스킵
                        // 이전에 이발을 사용한적 없어도 스킵
                        if (k == lastCommand || moves[lastTurn][k] == Integer.MAX_VALUE) continue;
                        // 저번 명령이 아니었으면서 발이 이번 명령에 올라갔던 적이 있다.
                        if (k == thisCommand)
                            min = Math.min(min, moves[lastTurn][k] + 1);
                        // k와 이번 명령의 홀짝이 동일한지 비교
                        int kEven = k % 2;
                        // 맞으면 건너편에서 건너온거다. +4
                        if (thisEven == kEven)
                            min = Math.min(min, moves[lastTurn][k] + 4);
                        // 다르면 옆에서 이동한거다.
                        else
                            min = Math.min(min, moves[lastTurn][k] + 3);
                    }
                    // 가장 적게 움직인게 이번에 최소인 움직임이다.
                    moves[thisTurn][j] = min;
                }
                // 아니라면 이전에 움직이지 않은발을 또 안움직여야 가능한 조합이다.
                else {
                    int lastMove = moves[lastTurn][j];
                    if (lastMove == Integer.MAX_VALUE) continue;
                    int lastEven = lastCommand % 2;
                    // 직전 명령과 홀짝이 같으면 +4
                    if (lastEven == thisEven) lastMove += 4;
                        // 다르면 + 3;
                    else lastMove += 3;
                    moves[thisTurn][j] = lastMove;
                }
            }
        }

        /*for (int[] move: moves) {
            System.out.println(Arrays.toString(move));
        }*/

        int min = Arrays.stream(moves[nonConCount - 1])
                .min()
                .orElse(0);
        System.out.println(min + (count - nonConCount));
    }
}
