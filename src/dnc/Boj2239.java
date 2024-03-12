package dnc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Boj2239 {
    private static final int[][] sudoku = new int[9][];
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            sudoku[i] = reader.readLine().chars()
                    .map(c -> c - '0')
                    .toArray();
        }
        dfs(0, 0);
        StringBuilder answerBuilder = new StringBuilder();
        for (int[] row: sudoku) {
            answerBuilder.append(Arrays.stream(row)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining("")));
            answerBuilder.append('\n');
        }
        System.out.print(answerBuilder);
    }

    private static boolean dfs(int y, int x) {
        int nextX = x + 1;
        int nextY = y;
        if (nextX == 9) {
            nextX = 0;
            nextY++;
        }
        // 0이 아니면 이미 숫자가 있다.
        if (sudoku[y][x] != 0) {
            // 마지막 칸이면 true
            if (nextY == 9) return true;
                // 아니면 다음칸
            else return dfs(nextY, nextX);
        }
        // 현재 칸에 넣을 수 있는걸 넣어보고, 확인한다.
        for (int i = 0; i < 9; i++) {
            sudoku[y][x] = i + 1;
            // 행, 열, 사각 조건 전부 만족
            if (checkCol(x) && checkRow(y) && checkSquare(y, x)) {
                // 만약 마지막 칸이면 true
                if (nextY == 9) return true;
                    // 아니면 다음 칸 호출
                else if(dfs(nextY, nextX)) return true;
            }
            sudoku[y][x] = 0;
        }
        return false;
    }

    // 열 확인
    private static boolean checkCol(int col) {
        boolean[] visited = new boolean[9];
        for (int i = 0; i < 9; i++) {
            if (sudoku[i][col] == 0) continue;
            if (visited[sudoku[i][col] - 1]) return false;
            visited[sudoku[i][col] - 1] = true;
        }
        return true;
    }

    // 행 확인
    private static boolean checkRow(int row) {
        boolean[] visited = new boolean[9];
        for (int i = 0; i < 9; i++) {
            if (sudoku[row][i] == 0) continue;
            if (visited[sudoku[row][i] - 1]) return false;
            visited[sudoku[row][i] - 1] = true;
        }
        return true;
    }

    // 사각형 확인
    private static boolean checkSquare(int y, int x) {
        boolean[] visited = new boolean[9];
        int startY = (y / 3) * 3;
        int startX = (x / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (sudoku[startY + i][startX + j] == 0) continue;
                if (visited[sudoku[startY + i][startX + j] - 1]) return false;
                visited[sudoku[startY + i][startX + j] - 1] = true;
            }
        }
        return true;
    }

}
