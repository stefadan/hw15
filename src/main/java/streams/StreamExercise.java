package streams;

import com.sun.security.jgss.GSSUtil;

import java.io.LineNumberInputStream;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.Stream;

public class StreamExercise {
    public static void main(String[] args) {
        List<Citizen> citizens = List.of(
                new Citizen("Mike", 28, "student", false),
                new Citizen("Amanda", 35, "artist", true),
                new Citizen("Nick", 24, "designer", true),
                new Citizen("Pete", 45, "artist", false)
        );

        Stream<Citizen> stream = citizens.stream();
        List<Citizen> citizenOver30 = citizens.stream()
                                        .filter(citizen -> citizen.age() > 30)
                                        .filter(citizen -> citizen.merried()).toList();

        System.out.println(citizenOver30);

        citizens.stream().map(citizen -> citizen.name())
                         .map(name -> name.length());

        List<Integer> nameLength = citizens.stream()
                                    .map(citizen -> citizen.name())
                                    .map(String::length).toList();
        System.out.println("nameLength="+nameLength);

        Long count = citizens.stream().filter(Citizen::merried).count();
        System.out.println("Merried="+count);

        boolean areAllArtistsMerried = citizens.stream().filter(citizen -> citizen.proffesion().equals("artist"))
                         .allMatch(citizen -> citizen.merried());
        System.out.println("All artists merried? "+areAllArtistsMerried);

        boolean isAnyArtistMerried = citizens.stream().filter(citizen -> citizen.proffesion().equals("artist"))
                .anyMatch(citizen -> citizen.merried());

        System.out.println(isAnyArtistMerried);

        List<String> naturallySort = citizens.stream()
                .map(citizen -> citizen.name())
                .sorted()
                .toList();
        System.out.println("Sortare dupa nume="+naturallySort);

        List<Citizen> sortedCitizen =
                citizens.stream().sorted((c1, c2)->-Boolean.compare(c1.merried(), c2.merried())).toList();

        System.out.println(sortedCitizen);

        OptionalDouble averageAge =  citizens.stream().mapToInt(Citizen::age).average();
        System.out.println(averageAge);

    }

}

record Citizen(String name, int age, String proffesion, boolean merried) {
}
