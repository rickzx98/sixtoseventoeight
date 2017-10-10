package eight;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Streams {
    static class Person {
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    public static void main(String... args) {
        List<Person> people = Arrays.asList(new Person("Mike", 61),
                new Person("Henry", 52), new Person("David", 55),
                new Person("Ragnar", 35), new Person("Bjorn", 23));

        people.stream()
                .filter(person -> person.age > 50)
                .sorted((o1, o2) -> o1.name.compareTo(o2.name))
                .reduce((person0, person2) -> {
                    System.out.println("person 1: " + person0.name);
                    System.out.println("person 2: " + person2.name);
                    return person0;
                });

    }
}

