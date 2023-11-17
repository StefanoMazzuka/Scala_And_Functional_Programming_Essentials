package playground;

/**
 * @author Stefano Mazzuka on 30/10/2023
 */
public class JavaPlayground {
    public static void main(String[] args) {
        System.out.println("Hello, Java");
        System.out.println(Person.N_EYES);
    }
}

class Person {
    public static final int N_EYES = 2;
}