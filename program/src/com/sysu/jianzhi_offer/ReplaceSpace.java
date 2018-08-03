package com.sysu.jianzhi_offer;

//请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
public class ReplaceSpace {
    public static String replaceSpace(StringBuffer str) {
        StringBuffer s = new StringBuffer();

        for (int i = 0; i < str.length(); i++) {
            System.out.println(str.charAt(i));
            if (str.charAt(i) == ' ') {
                s.append("%20");
            } else {
                s.append(str.charAt(i));
            }
        }

        return  s.toString();
    }

    public static void main(String[] args) {
        StringBuffer buff = new StringBuffer();
        buff.append("We Are Happy.");
        System.out.println(buff.toString());
        System.out.println(replaceSpace(buff));
    }
}
