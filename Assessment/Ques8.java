package Assessment;
import java.io.*;
import java.util.Arrays;
import java.util.Collections;
public class Ques8 {
    static boolean areAnagram(String s1,String s2)
    {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int n1 = str1.length;
        int n2 = str2.length;
        if (n1 != n2)
            return false;
        Arrays.sort(str1);
        Arrays.sort(str2);
        for (int i = 0; i < n1; i++)
            if (str1[i] != str2[i])
                return false;
        return true;
    }
    public static void main(String args[])
    {
        String s1 = "listen";
        String s2 = "silent";
        if (areAnagram(s1, s2))
            System.out.println("The two strings are"
                    + " anagram of each other");
        else
            System.out.println("The two strings are not"
                    + " anagram of each other");
    }
}



