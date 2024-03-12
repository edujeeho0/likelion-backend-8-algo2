package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NQueen {
    // N
    private static int size;
    // 총 놓을 수 있는 방법의 갯수
    private static int count;
    // 각 줄에 몇번째 칸에 Queen을 두엇는지
    private static int[] queenPos;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(reader.readLine());
        queenPos = new int[size];
        count = 0;
        setQueenPos(0);
        System.out.println(count);
    }

    // 몇번째 줄에 놓는지를 받아,
    // 해당하는 줄의 N개 중 하나에 Queen을 놓고
    // 다음줄에 Queen을 놓도록 재귀호출 하는 DFS
    private static void setQueenPos(
            // 몇번째 줄에 놓을 차례인지
            int row
    ) {
        // 마지막 줄까지 채웠다
        if (row == size) count++;
        else {
            for (int i = 0; i < size; i++) {
                // 일단 Queen을 배치해본 뒤,
                queenPos[row] = i;
                // 다음 재귀호출을 하기 전에 Queen들이 서로를
                // 위협하지 않음을 확인한다.
                if (checkQueen(row)) setQueenPos(row + 1);
            }
        }
    }

    private static boolean checkQueen(
            // 몇번째 줄까지 Queen을 넣었는지,
            // 그 줄까지만 확인하면 된다.
            int row
    ) {
        // Queen이 있는 모든 줄을 비교한다.
        for (int i = 0; i < row; i++) {
            // 만약 같은 col에 Queen이 배치된적 있다면 실패 (유망하지 않음)
            if (queenPos[i] == queenPos[row])
                return false;
            // 같은 대각선 상이라면
            // y와 x의 차이의 절대값이 동일하다.
            else if (Math.abs(queenPos[i] - queenPos[row]) == row - i)
                return false;
        }
        // 현재 배치된 Queen을 모두 확인했다면,
        // 예는 가능성이 남아있다.
        return true;
    }
}
