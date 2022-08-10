package org.example;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {

    public String say(int s) {
        if(s == 0) {
            throw new IllegalArgumentException("Input must grater than 0");
        }
        if( s%3 == 0 && s%5 == 0) {
            return "FizzBuzz";
        }

        if(s%3 == 0) {
            return "Fizz";
        }
        if(s%5 == 0) {
            return "Buzz";
        }
        return String.valueOf(s);
    }

    public List range(int start, int end){
        List<String> result = new ArrayList();
        for(int i=start; i<=end; i++) {
            result.add(say(i));
        }
        return result;
    }

    public static void main(String args[]) {

       System.out.println(new FizzBuzz().range(1,15));
    }
}
