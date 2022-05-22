package my.bai1;

import java.util.*;

/**
 * Yêu cầu: Cho mảng A chứa n phần tử số nguyên, hãy tìm và in ra 2 số có tổng nhỏ nhất trong mảng.
 *      Hãy mô tả thuật toán của bạn để thực hiện công việc trên với n<=1000.
 * Thuật toán: sắp xếp mảng A theo thứ tự tăng dần, sau đó lấy tổng 2 phần tử đầu dãy
 */
public class Bai1 {
    public static int patition(List<Integer> list, int l, int h) {
        int pivot = list.get(h);
        int left = l;
        int right = h - 1;
        while (true) {
            while (left <= right && list.get(left) < pivot) left++;
            while (right >= left && list.get(right) > pivot) right--;
            if (left >= right) break;
            Collections.swap(list, left, right);
            left++;
            right--;
        }
        Collections.swap(list, left, h);
        return left;
    }

    public static void sort(List<Integer> list, int l, int h) {
        if (l < h) {
            int pi = patition(list, l, h);

            sort(list, l, pi - 1);
            sort(list, pi + 1, h);
        }
    }

    public static List<Integer> random(int n) {
        List<Integer> rndList = new ArrayList<>();
        Random random = new Random();
        int tmp;
        for (int i = 0; i < n; i++) {
            tmp = (random.nextInt() % Integer.MAX_VALUE - Integer.MIN_VALUE + 1) + Integer.MIN_VALUE;
            rndList.add(tmp);
        }
        return rndList;
    }

    public static void main(String[] args) {
        List<Integer> a = random(10000);
        long s = System.currentTimeMillis();
        sort(a, 0, a.size() - 1);
        System.out.println(a.get(0) + a.get(1));
        long e = System.currentTimeMillis();
        System.out.println(e - s);
    }
}
