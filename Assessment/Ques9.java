package Assessment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ques9 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        int length;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter length");
        length = sc.nextInt();
        System.out.println("Enter elements of list");
        for (int i = 0 ; i < length ; i++){
            list.add(sc.nextInt());
        }
        List<Integer> newList = new ArrayList<>();
        for (Integer i : list){
            if (i > 0){
                newList.add(i);
            }
        }
        list = newList;
        System.out.println("List after removing negative elements");
        for (Integer i : list){
            System.out.println(i);
        }
    }
}
