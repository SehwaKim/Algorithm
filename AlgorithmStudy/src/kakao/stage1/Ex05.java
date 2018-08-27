package kakao.stage1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
FRANCE
french
16384

handshake
shake hands
65536

aa1+aa2
AAAA12
43690

E=M*C^2
e=m*c^2
65536

* */
public class Ex05 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();
        List<String> set1 = extractValidElements(str1);
        List<String> set2 = extractValidElements(str2);

        if (set1.isEmpty() && set2.isEmpty()) {
            printResult(1);
            return;
        }

        int intersectionSize = 0;
        int unionSize = 0;

        while (!set1.isEmpty()) {
            String target = set1.get(0);
            int targetSizeOfSet1 = 0;
            int targetSizeOfSet2 = 0;
            while (set1.contains(target)) {
                set1.remove(target);
                targetSizeOfSet1++;
            }
            while (set2.contains(target)) {
                set2.remove(target);
                targetSizeOfSet2++;
            }
            if (targetSizeOfSet2 > 0) {
                intersectionSize += Math.min(targetSizeOfSet1, targetSizeOfSet2);
                unionSize += Math.max(targetSizeOfSet1, targetSizeOfSet2);
            } else {
                unionSize += targetSizeOfSet1;
            }
        }

        // 이제 set2에는 A하고 겹치지 않았던 원소들이 각 1개씩 있음
        // 얘네들은 그냥 합집합에 더해주면 됨
        unionSize += set2.size();

        printResult((double) intersectionSize / unionSize);
    }

    private static void printResult(double num) {
        System.out.println((int) (num * 65536));
    }

    private static List<String> extractValidElements(String str) {
        List<String> set = new ArrayList<>();
        for (int i = 0; i < str.length() - 1; i++) {
            String temp = str.substring(i, i + 2);
            if (!((temp.charAt(0) >= 'A' && temp.charAt(0) <= 'Z')
                    || (temp.charAt(0) >= 'a' && temp.charAt(0) <= 'z'))) {
                continue;
            }
            if (!((temp.charAt(1) >= 'A' && temp.charAt(1) <= 'Z')
                    || (temp.charAt(1) >= 'a' && temp.charAt(1) <= 'z'))) {
                continue;
            }
            set.add(temp.toUpperCase());
        }
        return set;
    }
}
