package streams.examples;

import streams.data.Dish;

import java.util.List;
import java.util.stream.Stream;

public class PrimitiveTypeStreamExample {

   public static int sumCalories(List<Dish> menu){
       return menu.stream()
               .mapToInt(Dish::getCalories)
               .sum();
   }

   public static Stream<Integer> boxing(List<Dish> menu){
       return menu.stream()
               .mapToInt(Dish::getCalories)
               .boxed();
   }
}
