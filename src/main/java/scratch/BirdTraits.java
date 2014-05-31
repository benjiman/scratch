package scratch;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;


/**
 * Translation of http://joelabrahamsson.com/learning-scala-part-seven-traits/ into Java
 */
public class BirdTraits {

    public static void main(String... args) {
        List<Flying> flyingBirds = asList(new Pigeon(), new Hawk(), new FrigateBird());

        flyingBirds.forEach(Flying::fly);

        List<Swimming> swimmingBirds = asList(new Pigeon(), new Hawk(), new Penguin());

        swimmingBirds.forEach(Swimming::swim);
    }

    interface Bird {}

    interface Flying {
        String flyMessage();
        default void fly() {
            System.out.println(flyMessage());
        }
    }

    interface Swimming {
        default void swim() { System.out.println("I'm swimming"); }
    }

    static class Penguin implements Bird, Swimming { }
    static class Pigeon implements Bird, Swimming, Flying {
        public String flyMessage() {
            return "I'm a good flier";
        }
    }
    static class Hawk implements Bird, Swimming, Flying {
        public String flyMessage() {
            return "I'm an excellent flier";
        }
    }
    static class FrigateBird implements Bird, Flying {
        public String flyMessage() {
            return "I'm an excellent flier";
        }
    }

}
