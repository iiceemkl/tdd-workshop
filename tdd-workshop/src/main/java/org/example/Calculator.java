package org.example;

import java.util.Random;

public class Calculator {
    public int multiply(int x, int y){
        int sum = 0;
        for(int i=0; i<y; i++) {
            sum = add(sum, x);
        }
        return sum;
    }

    public int div(int x, int y) {
        if(y==0){
            throw new IllegalArgumentException("Division by 0");
        }else {
            return x/y;
        }
    }

    public int add(int x, int y) {
        return x+y;
    }

    public int del(int x, int y) {
        return x-y;
    }

    public int magicRandom(Random random) {
        return random.nextInt();
    }
}
