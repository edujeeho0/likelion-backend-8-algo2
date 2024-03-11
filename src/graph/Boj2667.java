package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://www.acmicpc.net/problem/2667
public class Boj2667 {
    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};
    private static int n;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            String line = reader.readLine();
            for (int j = 0; j < n; j++) {
                char col = line.charAt(j);
                map[i][j] = col - '0';
            }
        }

        // 모든 집이 있을 수 있는 지도의 칸을 반복해서 방문하는데,
        List<Integer> counts = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 만약 해당하는 칸에 집이 있을 경우,
                if (map[i][j] == 1) {
                    // 해당 칸을 시작점으로 BFS 또는 DFS를 한다.
                    // 그리고 그 과정에서 방문할 수 있었던 집의 갯수를 반환한다.
                    counts.add(bfs(i, j));
                }
            }
        }
    }

    // y, x를 시작점으로 BFS를 하고 최종적으로 방문한 집의 갯수를
    // 반환하는 메서드
    private static int bfs(int y, int x) {
        Queue<int[]> toVisit = new LinkedList<>();
        toVisit.offer(new int[]{y, x});
        // 집을 방문하면서 0으로 바꿔준다.
        map[y][x] = 0;
        int count = 0;
        while (!toVisit.isEmpty()) {
            int[] now = toVisit.poll();
            // 방문 할때마다 값을 늘려준다.
            count++;
            for (int i = 0; i < 4; i++) {
                int nextY = now[0] + dy[i];
                int nextX = now[1] + dx[i];
                if (
                        checkBounds(nextY, nextX) &&
                                map[nextY][nextX] == 1
                ) {
                    map[nextY][nextX] = 0;
                    toVisit.offer(new int[]{nextY, nextX});
                }
            }
        }

        return count;
    }

    private static boolean checkBounds(int y, int x) {
        return -1 < y && y < n && -1 < x && x < n;
    }

}
