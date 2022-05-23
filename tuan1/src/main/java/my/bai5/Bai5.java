package my.bai5;

import java.util.*;

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

public class Bai5 {
    public static void main(String[] args) {
        String tmp = "1 0 0 0 0 0 0 0 0 0 0 0 0 0 \n" +
                     "1 1 1 1 1 1 1 1 1 1 1 1 1 0 \n" +
                     "1 1 1 1 1 1 1 1 1 1 1 1 1 1 \n" +
                     "0 0 0 0 0 0 0 0 0 0 0 1 1 0 \n" +
                     "0 0 0 0 0 1 1 1 1 1 1 1 0 1 \n" +
                     "1 1 1 1 1 1 1 0 0 0 0 0 0 1 \n" +
                     "1 1 1 1 1 0 1 1 1 1 1 1 0 0 \n" +
                     "0 1 1 1 1 0 1 1 0 0 0 1 1 0 \n" +
                     "1 1 1 1 1 0 0 0 0 0 1 0 0 1 \n" +
                     "0 0 0 0 0 0 0 0 0 0 0 1 1 1 ";

        int[][] m = new int[10][14];
        generMatrix(tmp, m);
        int[][] matrix = concertMatrix(m, 10, 14);

        Scanner scanner = new Scanner(System.in);
        System.out.println("start"); // Nhận điểm bắt đầu
        int iS = scanner.nextInt();
        int jS = scanner.nextInt();
        System.out.println("end"); // Nhận điểm kết thúc
        int iT = scanner.nextInt();
        int jT = scanner.nextInt();
        // Ma trận được trèn thêm tường bao => Vị trí điểm đến và đi +1
        Point start = new Point(iS + 1, jS + 1);
        Point end = new Point(iT + 1, jT + 1);

        System.out.println(check(matrix, start, end));
    }

    /**
     * Kiểm tra có đường đi từ nguồn đến đích không.
     * @param matrix ma trận
     * @param start điểm nguồn
     * @param end điểm đích
     * @return true ỏ false
     */
    public static boolean check(int[][] matrix, Point start, Point end) {
        Stack<Point> stack = new Stack<>();
        Set<Point> visisted = new HashSet<>();
        stack.add(start);
        Point state;
        while (!stack.isEmpty()) {
            state = stack.pop();
            if (state.equals(end)) return true;
            visisted.add(state);
            for (Point nextState : listNext(matrix, state, visisted)) {
                if (!visisted.contains(nextState)) {
                    stack.add(nextState);
                }
            }
        }
        return false;
    }

    /**
     * Danh sách các điểm cạnh có thể đi đến.
     * @param matrix ma trận
     * @param state điểm hiện tại
     * @param visisted tập các điểm đã đi qua
     * @return danh sách
     */
    public static List<Point> listNext(int[][] matrix, Point state, Set<Point> visisted) {
        List<Point> nextNode = new ArrayList<>();
        Point point;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                point = new Point(state.x + i, state.y + j);
                if (i + j != 0 && i * j == 0 && matrix[state.x + i][state.y + j] == 1 &&
                        !visisted.contains(point)) {
                    nextNode.add(point);
                }
            }
        }
        return nextNode;
    }

    /**
     * Tạo ra ma trận mảng 2 chiều từ chuỗi string
     * @param matrixString chuỗi tring
     * @param matrix ma trận được tạo ra
     */
    public static void generMatrix(String matrixString, int[][] matrix) {
        StringTokenizer stringTokenizer = new StringTokenizer(matrixString, "\n");
        StringTokenizer st;
        int i = 0;
        String tmp;
        while (stringTokenizer.hasMoreTokens()) {
            tmp = stringTokenizer.nextToken();
//            System.out.println(tmp);
            int j = 0;
            st = new StringTokenizer(tmp, " \n");
            while (st.hasMoreTokens()) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                j++;
            }
            i++;
        }
    }

    public static void printMatrix(int[][] matrix, int m, int n) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * chèn thêm tường bao quanh ma trận (thêm điểm 0 xung quanh).
     * @param matrix ma trận
     * @param m số hàng
     * @param n số cột
     * @return ma trận đã chèn
     */
    public static int[][] concertMatrix(int[][] matrix, int m, int n) {
        int[][] res = new int[m + 2][n + 2];
        for (int i = 0; i < m + 2; i++) {
            for (int j = 0; j < n + 2; j++) {
                if (i == 0 || j == 0 || i == m + 1 || j == n + 1) {
                    res[i][j] = 0;
                } else {
                    res[i][j] = matrix[i - 1][j - 1];
                }
            }
        }
        return res;
    }
}
