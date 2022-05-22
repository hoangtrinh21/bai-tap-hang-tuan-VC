package my.bai2;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Bai2 {
    public static Set<Integer> random(int n) {
        Set<Integer> rndList = new TreeSet<>();
        Random random = new Random();
        int tmp;
        while (rndList.size() < n) {
            tmp = (random.nextInt() % Integer.MIN_VALUE - Integer.MIN_VALUE) + Integer.MIN_VALUE;
            rndList.add(tmp);
        }
        return rndList;
    }

    public static void main(String[] args) {
        int k = new Scanner(System.in).nextInt();
        long start = System.currentTimeMillis();
        Set<Integer> set = random(1000000);
        Map<Integer, Integer> res = new HashMap<>();
        int i = 0, a, b;
        for (Integer s : set) {
            if (i > k/2) break;
            a = s.intValue();
            b = k - a;
            if (set.contains(b)) {
                res.put(a, b);
            }
            i++;
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start + "ms");
        System.out.println(res);
    }
}