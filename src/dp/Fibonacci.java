package dp;

public class Fibonacci {
    private int count = 0;

    // 단순한 재귀호출로 n번째 피보나치 수를 구하는 메서드
    public int fiboSimple(int n) {
        count++;
        // n < 0 -> 0
        if (n < 1) return 0;
        // a1 = 1, a2 = 1
        if (n == 1 || n == 2) return 1;
        // an = a(n-1) + a(n-2)
        return fiboSimple(n - 1) + fiboSimple(n - 2);
    }

    // Memoization을 활용한 n번째 피보나치 수를 구하는 메서드
    public int fiboMemo(int n) {
        count++;
        // 메모장을 준비한다. n번 숫자를 찾는 것이기 때문에,
        // [n + 1] 공간을 마련한다.
        int[] memo = new int[n + 1];
        // 재귀호출로 피보나치 수를 구하면
        // memo[n]에 저장된 숫자가 최종적인 구하고자 하는 숫자다.
        return fiboMemoRe(n, memo);
    }

    private int fiboMemoRe(int n, int[] memo) {
        count++;
        if (n < 1) return 0;
        if (n == 1 || n == 2)
            return 1;
        else {
            // 아직 메모장에 결과를 저장한적 없는 n일 경우,
            if (memo[n] == 0) {
                // 새로 작성해준다.
                memo[n] = fiboMemoRe(n - 1, memo) + fiboMemoRe(n - 2, memo);
            }
        }
        return memo[n];
    }

    public int fiboTab(int n) {
        if (n < 1) return 0;
        if (n == 1 || n == 2) return 1;
        // 다음 정답을 구하기 위해 사용하는,
        // 여태까지의 문제의 해답
        int[] fib = new int[n + 1];
        // 1과 2의 해답은 알고 있다.
        fib[1] = 1;
        fib[2] = 1;
        // n번의 해답은 fib[n - 1] + fib[n - 2]
        // for문을 이용해 fib 배열을 채워나가며 fib[n]을 찾으면 된다.
        for (int i = 3; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib[n];
    }

    public static void main(String[] args) {
        Fibonacci fibo = new Fibonacci();
        int n = 40;
        long start = System.nanoTime();
//        System.out.println(fibo.fiboSimple(n));
//        System.out.println(fibo.count);
//        System.out.println(String.format("elapsed: %d", System.nanoTime() - start));
//        fibo.count = 0;
        start = System.nanoTime();
        System.out.println(fibo.fiboMemo(n));
        System.out.println(fibo.count);
        System.out.println(String.format("elapsed: %d", System.nanoTime() - start));

        start = System.nanoTime();
        System.out.println(fibo.fiboTab(n));
        System.out.println(String.format("elapsed: %d", System.nanoTime() - start));

    }
}
