package io.windfree.algorithm.recursion;

public class Code01 {
    public static void main(String[] args) {
        func(5);

    }
    public static void func(int n) {
        if (n <= 0) return;
        System.out.println("Hello " + n);
        func(n-1);
        System.out.println("bye " + n);
    }
}
