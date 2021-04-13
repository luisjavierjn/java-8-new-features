package model.apples;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Apple {

    int weight;
    Color color;


    public static boolean isReadAndHeavyApple(Apple apple) {
        System.out.println("Complex code here!");
        return Color.RED.equals(apple.getColor()) && apple.getWeight() > 100;
    }


}



























/*
    public static boolean isReadAndHeavyApple(Apple apple) {
        System.out.println("Complex code here!");
        return Color.RED.equals(apple.getColor()) && apple.getWeight() > 100;
    }
 */
