package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Maximum {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(10);
        list.add(29);
        list.add(1);
        list.add(66);
        list.add(90);
        System.out.print("Array list: ");
        for(Integer i :list)
        {
            System.out.print(i+" ");
        }
        System.out.println("");
        System.out.println("First Maximum element : "+ FirstMax(list));
        System.out.println("Second Maximum element : "+SecondMax(list));
        int k;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter value of k");
        k = sc.nextInt();
        MaxTillK(list,k);
    }
    public static int FirstMax(ArrayList<Integer> list)
    {
        return Collections.max(list);
    }
    public static int SecondMax(ArrayList<Integer> list)
    {
        Collections.sort(list, Collections.reverseOrder());
        return list.get(1);
    }
    public static void MaxTillK(ArrayList<Integer> list,int k)
    {
        if(k <= list.size()) {
            Collections.sort(list, Collections.reverseOrder());
            System.out.print("Maximum upto " + k + " : ");
            for (int i = 0; i < k; i++) {
                System.out.print(list.get(i) + " ");
            }
        }
        else {
            System.out.println("k exceeds size of list");
        }
    }

}