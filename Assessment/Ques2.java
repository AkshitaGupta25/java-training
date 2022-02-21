package Assessment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ques2 {
    public static boolean pairSum(List<Integer> list, int sum){
        for (int i = 0 ; i < list.size(); i++){
            for (int j = i+1 ; j < list.size() ; j++){
                if(list.get(i) + list.get(j) == sum){
                    return true;
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int length;
        List<Integer> list = new ArrayList<>();
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter length of list");
        length = sc.nextInt();
        int sum;
        System.out.println("Enter Sum");
        sum = sc.nextInt();
        System.out.println("Enter elements of list");
        Integer x;
        for (int i = 0 ; i < length ; i++){
            x = sc.nextInt();
            list.add(x);
        }
        System.out.println(pairSum(list,sum));
    }
}
