package streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class PersonService {

    public static void main(String[] args) {
        List<Person> person = List.of(
                new Person("Ana", "Popa", 10, "Oradea"),
                new Person("Dan", "Roman", 20, "Cluj"),
                new Person("Oana", "Dima", 30, "Cluj"),
                new Person("Radu", "Dima", 40, "Oradea"),
                new Person("Ina", "Pop", 50, "Oradea"),
                new Person("Ana", "Pop", 62, "Oradea")
        );

        Stream<Person> personStream = person.stream();

        //list all the persons names: firstName lastName
        List<String> nameList = person.stream()
                .map(pers -> pers.firstName().concat(" ".concat(pers.lastName()))).collect(toList());
        System.out.println("First name and last name: " + nameList);

        //list all persons that are major
        List<Person> allMajorPerson = person.stream()
                .filter(pers -> pers.age() > 18).toList();
        System.out.println("Person tha are major: " + allMajorPerson);

        //list all persons from Oradea
        List<Person> allFromOradea = person.stream()
                .filter(pers -> pers.city().toUpperCase().equals("ORADEA")).toList();
        System.out.println("All from Oradea: " + allFromOradea);

        //list all persons from Oradea OR Cluj
        List<Person> allFromOradeaOrCluj = person.stream()
                .filter(p -> p.city().equals("Oradea") || p.city().equals("Cluj")).toList();
        System.out.println("All from Cluj and Oradea:" + allFromOradeaOrCluj);


        //list all firstNames CAPITALIZED
        List<String> firstNamesCapitalized = person.stream()
                .map(pers -> pers.firstName().toUpperCase()).collect(toList());
        System.out.println(firstNamesCapitalized);

        //list all person names: firstName firstletter from last name: Stefan B.
        List<String> nameListFirstLetter = person.stream()
                .map(pers -> pers.firstName().concat(" ".concat(pers.lastName().substring(0, 1).toUpperCase())).concat(".")).collect(toList());
        System.out.println("First letter: " + nameListFirstLetter);

        //list all persons with 18 < age < 60
        List<Person> allBetween18and60 = person.stream()
                .filter(p -> p.age() > 18)
                .filter(p -> p.age() < 60)
                .toList();
        System.out.println("Age 18-60: " + allBetween18and60);

        //list all persons having first name starting with A
        List<Person> firstNameStratingWithA = person.stream()
                .filter(p -> p.firstName().startsWith("A"))
                .toList();
        System.out.println("First name starting with A:" + firstNameStratingWithA);

        //list all first names UNIQUELY
        Set<String> set = new HashSet<>(person.size());
        List<Person> firstNameUnique = person.stream()
                .filter(p -> set.add(p.firstName())).collect(Collectors.toList());

        System.out.println("First names uniquely:" + firstNameUnique);

        //sort the persons by first name
        List<String> naturallyFirstNameSort = person.stream()
                .map(pers -> pers.firstName())
                .sorted()
                .toList();
        System.out.println("Sort by firstname=" + naturallyFirstNameSort);

        //sort the persons by last name
        List<String> naturallyLastNameSort = person.stream()
                .map(pers -> pers.lastName())
                .sorted()
                .toList();
        System.out.println("Sort by lastName=" + naturallyLastNameSort);

        //sort the persons by first name, last name and then age
        //first method
        List<Person> sortedList = person.stream()
                .sorted(Comparator.comparing(Person::firstName)
                        .thenComparing(Person::lastName)
                        .thenComparing(Person::age))
                .collect(toList());

        System.out.println("Sorted list=" + sortedList);

        //second method
        Comparator<Person> byFirstName = (p, q) -> p.firstName().compareToIgnoreCase(q.firstName());
        Comparator<Person> byLastName = (p, q) -> p.lastName().compareToIgnoreCase(q.lastName());
        Comparator<Person> byAge = (p, q) -> Integer.compare(p.age(), q.age());
        List<Person> per = person.stream().sorted(byFirstName
                .thenComparing(byLastName)
                .thenComparing(byAge)
        ).collect(toList());
        System.out.println("Sorted list by comparator: " + per);
    }
}


record Person(String firstName, String lastName, int age, String city) {
}
