package java8.behavior_parametrization;

import model.apples.Apple;
import model.apples.Color;

import java.util.ArrayList;
import java.util.List;

// 1. Passing code with "Behavior parameterization"
// 2. Methods and Lambdas as first-class citizen
public class FilteringApples {

    public static void main(String[] args) {
        List<Apple> inventory = buildInventory();

        for(Apple apple : inventory) {
            System.out.println(apple);
        }

    }

    // First attempt: filtering green apples
    // List<Apple> greenApples = filterGreenApples(inventory);
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple: inventory) {
            if(Color.GREEN.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    // Second attempt: parameterizing the color
    // List<Apple> greenApples = filterApplesByColor(inventory, Color.GREEN);
    // List<Apple> redApples = filterApplesByColor(inventory, Color.RED);
    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory) {
            if (apple.getColor().equals(color) ) {
                result.add(apple);
            }
        }
        return result;
    }

    // Third attempt: filtering with every attribute you can think of
    // List<Apple> greenApples = filterApples(inventory, Color.GREEN, 0, true);
    // List<Apple> heavyApples = filterApples(inventory, null, 100, false);
    public static List<Apple> filterApples(List<Apple> inventory, Color color, int weight, boolean flag) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple: inventory) {
            if ((flag && apple.getColor().equals(color))
                    || (!flag && apple.getWeight() > weight)){
                result.add(apple);
            }
        }
        return result;
    }

    // Behavior parameterization
    // *************************
    interface ApplePredicate {
        boolean test (Apple apple);
    }

    class AppleGreenColorPredicate implements ApplePredicate {
        public boolean test(Apple apple) {
            return Color.GREEN.equals(apple.getColor());
        }
    }

    class AppleHeavyWeightPredicate implements ApplePredicate {
        public boolean test(Apple apple) {
            return apple.getWeight() > 100;
        }
    }

    public class AppleRedAndHeavyPredicate implements ApplePredicate {
        public boolean test(Apple apple){
            return Color.RED.equals(apple.getColor())
                    && apple.getWeight() > 100;
        }
    }

    // Fourth attempt: filtering by abstract criteria
    // List<Apple> greenApples = filterApples(inventory, new AppleGreenColorPredicate());
    // List<Apple> heavyApples = filterApples(inventory, new AppleHeavyWeightPredicate());
    // List<Apple> redAndHeavyApples = filterApples(inventory, new AppleRedAndHeavyPredicate());
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple: inventory) {
            if(p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    // Fifth attempt: using an anonymous class
    static {
        List<Apple> inventory = buildInventory();

        List<Apple> redApples = filterApples(inventory,
                new ApplePredicate() {
                    public boolean test(Apple apple){
                        return Color.RED.equals(apple.getColor());
                    }
                });
    }

    // Sixth attempt: using a lambda expression
    static {
        List<Apple> inventory = buildInventory();
        List<Apple> redApples = filterApples(inventory,
                (Apple apple) -> Color.RED.equals(apple.getColor()) );

        List<Apple> redAndHeavyApples = filterApples(inventory,
                (Apple apple) -> Color.RED.equals(apple.getColor()) && apple.getWeight() > 100 );
    }


    // Methods and Lambdas as first-class citizen
    // ******************************************
    static {
        List<Apple> inventory = buildInventory();

        ApplePredicate greenApples = (Apple a) -> Color.GREEN.equals(a.getColor());
        ApplePredicate readAndHeavyApples = Apple::isReadAndHeavyApple;

        List<Apple> redApples = filterApples(inventory, greenApples);
        List<Apple> redAndHeavyApples = filterApples(inventory, readAndHeavyApples);
    }

    private static List<Apple> buildInventory(){
        return List.of(
                Apple.builder().color(Color.GREEN).weight(70).build(),
                Apple.builder().color(Color.GREEN).weight(90).build(),
                Apple.builder().color(Color.GREEN).weight(110).build(),
                Apple.builder().color(Color.RED).weight(80).build(),
                Apple.builder().color(Color.RED).weight(120).build(),
                Apple.builder().color(Color.YELLOW).weight(95).build()
        );
    }
}
