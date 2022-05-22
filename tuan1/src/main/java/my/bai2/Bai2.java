package my.bai2;

import java.util.*;

public class Bai2 {
    public static Set<Integer> random(int n) {
        Set<Integer> rndList = new HashSet<>();
        Random random = new Random();
        int tmp;
        while (rndList.size() < n) {
            tmp = (random.nextInt() % Integer.MAX_VALUE - Integer.MIN_VALUE + 1) + Integer.MIN_VALUE;
            rndList.add(tmp);
        }
        return rndList;
    }

    public static void main(String[] args) {
        Set<Integer> set = random(1000);
        int k = new Scanner(System.in).nextInt();


    }
}
