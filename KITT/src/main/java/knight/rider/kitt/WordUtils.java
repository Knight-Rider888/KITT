package knight.rider.kitt;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class WordUtils {


    /**
     * 拆分字符串信息，包含：全部字符数组，非数字英文的字符数组
     * 大写字母个数、小写字母个数、数字个数
     *
     * @param word the file to be check.
     */
    public static WordInfo getWordInfo(String word) {

        WordInfo wordInfo = new WordInfo();

        if (!TextUtils.isEmpty(word)) {

            char[] c = word.toCharArray();
            wordInfo.setAllChar(c);

            int daXie = 0;
            int xiaoXie = 0;
            int num = 0;
            List<Integer> other = new ArrayList<>();

            for (int i = 0; i < word.length(); i++) {
                if (c[i] >= 'a' && c[i] <= 'z') {
                    xiaoXie++;
                } else if (c[i] >= 'A' && c[i] <= 'Z') {
                    daXie++;
                } else if (c[i] >= '0' && c[i] <= '9') {
                    num++;
                } else {
                    other.add(i);
                }
            }

            char[] others = new char[other.size()];
            for (int i = 0; i < other.size(); i++) {
                others[i] = c[other.get(i)];
            }

            wordInfo.setOtherChar(others);
            wordInfo.setUppercase(daXie);
            wordInfo.setLowercase(xiaoXie);
            wordInfo.setNumber(num);
        }

        return wordInfo;
    }


    public static class WordInfo {

        // 拆分所有字符
        private char[] allChar;
        // 除英文、数字外的字符
        private char[] otherChar;
        // 大写的数量
        private int uppercase;
        // 小写的数量
        private int lowercase;
        // 数字的数量
        private int number;

        public WordInfo() {
            this.allChar = new char[0];
            this.otherChar = new char[0];
            this.uppercase = 0;
            this.lowercase = 0;
            this.number = 0;
        }

        public char[] getAllChar() {
            return allChar;
        }

        public void setAllChar(char[] allChar) {
            this.allChar = allChar;
        }

        public char[] getOtherChar() {
            return otherChar;
        }

        public void setOtherChar(char[] otherChar) {
            this.otherChar = otherChar;
        }

        public int getUppercase() {
            return uppercase;
        }

        public void setUppercase(int uppercase) {
            this.uppercase = uppercase;
        }

        public int getLowercase() {
            return lowercase;
        }

        public void setLowercase(int lowercase) {
            this.lowercase = lowercase;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }

}
