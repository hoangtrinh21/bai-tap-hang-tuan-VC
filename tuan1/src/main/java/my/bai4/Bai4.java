package my.bai4;

import java.util.Stack;
import java.util.StringTokenizer;

public class Bai4 {
    public static void main(String[] args) {
        String xml = "<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "    <modelVersion>4.0.0</modelVersion>\n" +
                "\n" +
                "    <groupId>org.example</groupId>\n" +
                "    <artifactId>tuan1</artifactId>\n" +
                "    <version>1.0-SNAPSHOT</version>\n" +
                "\n" +
                "    <properties>\n" +
                "        <maven.compiler.source>11</maven.compiler.source>\n" +
                "        <maven.compiler.target>11</maven.compiler.target>\n" +
                "    </properties>\n" +
                "\n" +
                "</project>";
        System.out.println(checkXML(xml));
    }

    /**
     * Dùng stack1 tìm các thẻ mở và đóng, đưa các thẻ mở vừa tìm vào stack2.
     * Khi gặp thẻ đóng, kiểm tra thẻ mở đầu stack cơ hợp lệ không.
     * Tìm đến hết chuỗi string hoặc thẻ đóng không hợp lệ.
     * @param xml chuỗi
     * @return Sai khi thẻ đóng không hợp lệ hoặc stack không rỗng, ngược lại.
     */
    public static boolean checkXML(String xml) {
        Stack<Character> st = new Stack<>();
        Stack<String> stack = new Stack<>();
        String tmp;
        for (int i = 0; i < xml.length() - 1; i++) {
            if (xml.charAt(i) == '<') { // Bắt đầu phát hiện thẻ
                for (int j = i; j < xml.length(); j++, i++){
                    st.add(xml.charAt(j));
                    if (xml.charAt(j) == '>') { // Kí hiệu đóng thẻ
                        tmp = stackToString(st); // Chuyển thẻ vừa tìm thấy sang dạng string
                        st.clear();
                        if (tmp.charAt(1) == '/') { // Kiểm tra thẻ đóng true
                            String o = stack.pop();
                            if (!checkCloseTag(tmp, o)) { // Kiểm tra thẻ đóng khớp với thẻ mở
                                return false; // Khong khớp trả về false;
                            }
                        } else {
                            stack.add(tmp);
                        }
                        break;
                    }
                }
            }
        }
        return stack.isEmpty();
    }

    public static String stackToString(Stack<Character> stack) {
        String x = "";
        while (!stack.isEmpty()) {
            x = stack.pop() + x;
        }
        return x;
    }

    /**
     * Kiểm tra thẻ đóng và thẻ mở có hợp lệ không.
     * @param open thẻ mở
     * @param close thẻ đóng
     * @return true ỏ false
     */
    public static boolean checkCloseTag(String open, String close) {
        StringTokenizer st1 = new StringTokenizer(open, "</>");
        StringTokenizer st2 = new StringTokenizer(close, "< >");
        return st1.nextToken().equals(st2.nextToken());
    }
}
