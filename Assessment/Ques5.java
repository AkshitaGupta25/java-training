package Assessment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Ques5 {
    public static void getTopKMax(List<Integer> list, int k){
        Collections.sort(list,Collections.reverseOrder());
        for (int i = 0 ; i < k ; i++){
            System.out.println(list.get(i));
        }
    }

    public static void main(String[] args) {
        int length;
        List<Integer> list = new ArrayList<>();
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter length of list");
        length = sc.nextInt();
        int k;
        System.out.println("Enter K");
        k = sc.nextInt();
        System.out.println("Enter elements of list");
        Integer x;
        for (int i = 0 ; i < length ; i++){
            x = sc.nextInt();
            list.add(x);
        }
        getTopKMax(list,k);
    }
}
