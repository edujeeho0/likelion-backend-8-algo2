package dnc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1802 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int tests = Integer.parseInt(reader.readLine());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < tests; i++) {
            if (foldable(reader.readLine()))
                answer.append("YES\n");
            else answer.append("NO\n");
        }
        System.out.print(answer);
    }

    private static boolean foldable(String paper) {
        if (paper.length() > 1) {
            int half = paper.length() / 2;
            if (!foldable(paper.substring(half + 1))) return false;
            if (!foldable(paper.substring(0, half))) return false;
            for (int i = 1; i < half + 1; i++) {
                if (paper.charAt(half + i) == paper.charAt(half - i))
                    return false;
            }
        }
        return true;
    }
}
