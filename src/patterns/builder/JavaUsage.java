package patterns.builder;

public class JavaUsage {

    public static void main(String[] args) {
        Person person = new Person.Builder("JohnD", "test@test.com")
                .setFirstName("John")
                .setCity("LA")
                .build();

        System.out.print(person);
    }
}
