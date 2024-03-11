package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/11047
public class Boj11047 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] info = reader.readLine().split(" ");
        // 동전의 종류
        int coinKinds = Integer.parseInt(info[0]);
        // 만들고자 하는 금액
        int targetAmount = Integer.parseInt(info[1]);
        // 동전들의 개별 가치를 저장하기 위한 배열
        int[] coinAmounts = new int[coinKinds];
        // 큰게 나중에 들어옴으로, 뒤에서 부터 저장한다.
        for (int i = 0; i < coinKinds; i++) {
            coinAmounts[coinKinds - 1 - i] = Integer.parseInt(reader.readLine());
        }

        // 사용한 동전의 갯수
        int coinUsed = 0;
        // 동전의 종류만큼 반복한다.
        for (int i = 0; i < coinKinds; i++) {
            // 이번에 사용할 동전을 받아온다.
            int coinAmount = coinAmounts[i];
            // 이 동전을 몇개 사용할 수 있는지를 여태까지 사용한 동전의 갯수에 추가한다.
            coinUsed += targetAmount / coinAmount;
            // 동전을 사용한 후, 남은 금액을 갱신한다.
            targetAmount %= coinAmount;
        }
        System.out.println(coinUsed);
    }
}
