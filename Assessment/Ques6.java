package Assessment;

import java.util.Arrays;
import java.util.List;

public class Ques6 {
    public static void main(String[] args) {
        List<Integer> numberList = Arrays.asList(11,12,22,15,86,55,33,64);
        numberList.stream().filter(n -> n%2 == 0).forEach(System.out::println);
    }
}
