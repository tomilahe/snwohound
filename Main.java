import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;


public class Main {


    public static void main(String[] args) {
        User user = new User("TestUser", 10.0, new ArrayList<>());
        User user2 = new User("TestUser2", 10.0, new ArrayList<>());
        List<MovieShelf> shelves = new ArrayList<>();
        MovieRental rental = new MovieRental(shelves);
        rental.addUser(user);
        rental.addUser(user2);
        Movie m1 = new Movie("Name1", "1", "action", "Madal");
        Movie m2 = new Movie("Name2", "2", "action", "Madal");
        rental.add(m1);
        rental.add(m2);
        rental.lendMovies("1", 3, "Testuser");
        rental.lendMovies("2", 1, "Testuser2");
        System.out.println(user.getLendedMovies());
        System.out.println(user2.getLendedMovies());

    }
}
