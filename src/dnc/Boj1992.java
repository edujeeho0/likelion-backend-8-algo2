package dnc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1992 {
    private static char[][] image;
    private static final StringBuilder quadTree = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        image = new char[n][];
        for (int i = 0; i < n; i++) {
            image[i] = reader.readLine().toCharArray();
        }
        compressQuad(n, 0, 0);
        System.out.println(quadTree);
    }

    private static void compressQuad(
            // 지금 대상으로 확인할 영상의 크기
            int n,
            // 왼쪽 상단의 좌표
            int y, int x
    ) {
        // 전체가 일치하는 문자인지를 나타내는 flag
        boolean success = true;
        // 이 이미지가, 만약 전체가 일치하는 값이라면,
        // 제일 위쪽 값으로 압축이 될것이다.
        char init = image[y][x];
        // 2차원 배열을 y, x부터 순회하며 확인한다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 만약 원소 하나라도 왼쪽 위와 다르다면 실패다.
                if(image[y + i][x + j] != init) {
                    success = false;
                    break;
                }
            }
            if (!success) break;
        }
        // 만약 전체가 일치한다면, init으로 압축하면 된다.
        if (success) quadTree.append(init);
        else {
            // 괄호를 연다.
            quadTree.append('(');
            // 4등분한 영상을 대상으로 다시 실행한다.
            int half = n / 2;
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    compressQuad(half, y + half * i, x + half * j);
                }
            }
            // 괄호는 닫는다.
            quadTree.append(')');
        }
    }
}
