package Assessment;

public class Ques3 {
    public static int fibonacci(int n){
        int a = 0;
        int b = 1;
        int temp = 1;
        if(n == 0)
            return 0;
        if(n == 1)
            return 1;
        for (int i = 2 ; i <= n ; i++){
            temp = a+b;
            a = b;
            b = temp;
        }
        return temp;
    }

    public static int fibonacciRecursive(int n){
        if(n <= 1)
            return n;
        return fibonacciRecursive(n-1) + fibonacciRecursive(n-2);
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(8));
        System.out.println(fibonacciRecursive(8));
    }

}
