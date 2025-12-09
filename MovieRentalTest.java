import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MovieRentalTest {

    @Test
    public void AddMoviesTest() {
        List<MovieShelf> shelves = new ArrayList<>();

        MovieRental rental = new MovieRental(shelves);

        Movie m1 = new Movie("Name1", "1", "ACTION", "Madal");
        Movie m2 = new Movie("Name2", "2", "ACTION", "Madal");
        Movie m3 = new Movie("Name3", "3", "ACTION", "Madal");
        Movie m4 = new Movie("Name4", "4", "ACTION", "Madal");
        rental.add(m1);
        rental.add(m2);
        rental.add(m3);
        rental.add(m4);
        String out = rental.toString();
        assertTrue(out.contains("Name1"));
        assertTrue(out.contains("Name2"));
        assertTrue(out.contains("Name3"));
        assertTrue(out.contains("Name4"));
    }

    @Test
    public void GetMovieTest() {
        List<MovieShelf> shelves = new ArrayList<>();

        MovieRental rental = new MovieRental(shelves);

        Movie m1 = new Movie("Name1", "1", "ACTION", "Madal");
        Movie m2 = new Movie("Name2", "2", "ACTION", "Madal");
        Movie m3 = new Movie("Name3", "3", "ACTION", "Madal");
        Movie m4 = new Movie("Name4", "4", "ACTION", "Madal");
        rental.add(m1);
        rental.add(m2);
        rental.add(m3);
        rental.add(m4);
        Movie fetchedMovie = rental.getMovie("3");
        Assertions.assertNotNull(fetchedMovie);
        Assertions.assertEquals("Name3", fetchedMovie.getNimi());
    }

    @Test
    public void RemoveMovieTest() {
        List<MovieShelf> shelves = new ArrayList<>();

        MovieRental rental = new MovieRental(shelves);

        Movie m1 = new Movie("Name1", "1", "ACTION", "Madal");
        Movie m2 = new Movie("Name2", "2", "ACTION", "Madal");
        Movie m3 = new Movie("Name3", "3", "ACTION", "Madal");
        Movie m4 = new Movie("Name4", "4", "ACTION", "Madal");
        rental.add(m1);
        rental.add(m2);
        rental.add(m3);
        rental.add(m4);
        rental.removeMovie("2");
        String out = rental.toString();
        assertTrue(out.contains("Name1"));
        Assertions.assertFalse(out.contains("Name2"));
        assertTrue(out.contains("Name3"));
        assertTrue(out.contains("Name4"));
    }

    @Test
    public void GetAllCategoriesTest() {
        List<MovieShelf> shelves = new ArrayList<>();
        MovieRental rental = new MovieRental(shelves);
        Movie m1 = new Movie("Name1", "1", "ACTION", "Madal");
        Movie m2 = new Movie("Name2", "2", "DRAMA", "Madal");
        Movie m3 = new Movie("Name3", "3", "COMEDY", "Madal");
        Movie m4 = new Movie("Name4", "4", "HORROR", "Madal");
        rental.add(m1);
        rental.add(m2);
        rental.add(m3);
        rental.add(m4);

        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        rental.getAllCategories();

        String output = outContent.toString();
        assertTrue(output.contains("ACTION"));
        assertTrue(output.contains("DRAMA"));
        assertTrue(output.contains("COMEDY"));
        assertTrue(output.contains("HORROR"));
    }

    @Test
    public void getMoviesByCategoryTest() {
        List<MovieShelf> shelves = new ArrayList<>();
        MovieRental rental = new MovieRental(shelves);
        Movie m1 = new Movie("Name1", "1", "action", "Madal");
        Movie m2 = new Movie("Name2", "2", "drama", "Madal");
        Movie m3 = new Movie("Name3", "3", "ACTION", "Madal");
        Movie m4 = new Movie("Name4", "4", "DRAMA", "Madal");
        rental.add(m1);
        rental.add(m2);
        rental.add(m3);
        rental.add(m4);

        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        rental.getAllMovies("ACTION");

        String output = outContent.toString();
        assertTrue(output.contains("Name1"));
        assertTrue(output.contains("Name3"));
        Assertions.assertFalse(output.contains("Name2"));
        Assertions.assertFalse(output.contains("Name4"));
    }


    @Test
    public void checkPriceTest() {
        Movie m1 = new Movie("Name1", "1", "ACTION", "Madal");
        Movie m2 = new Movie("Name2", "2", "DRAMA", "Keskmine");
        Movie m3 = new Movie("Name3", "3", "HORROR", "Kõrge");

        double price1 = m1.getLendingPrice(3);
        double price2 = m2.getLendingPrice(2);
        double price3 = m3.getLendingPrice(4);
        double price4 = m1.getLendingPrice();
        double price5 = m2.getLendingPrice();
        double price6 = m3.getLendingPrice();
        double price7 = m3.getLendingPrice(0);

        Assertions.assertEquals(2.0, price1);
        Assertions.assertEquals(3.0, price2);
        Assertions.assertEquals(10.0, price3);
        Assertions.assertEquals(1.0, price4);
        Assertions.assertEquals(2.0, price5);
        Assertions.assertEquals(4.0, price6);
        Assertions.assertEquals(0.0, price7);
    }

    @Test
    public void checkLendingTest() {
        User user = new User("TestUser", 10.0, new ArrayList<>());
        List<MovieShelf> shelves = new ArrayList<>();
        MovieRental rental = new MovieRental(shelves);
        rental.addUser(user);
        Movie m1 = new Movie("Name1", "1", "action", "Madal");
        rental.add(m1);
        rental.lendMovies("1", 3, "TestUser");
        Assertions.assertEquals(8.0, user.getMoney());
        Assertions.assertEquals("Name1", user.getLendedMovies().get(0).getNimi());
    }

    @Test
    public void checkLendingInsufficientFundsTest() {
        User user = new User("TestUser", 2.0, new ArrayList<>());
        List<MovieShelf> shelves = new ArrayList<>();
        MovieRental rental = new MovieRental(shelves);
        rental.addUser(user);
        Movie m1 = new Movie("Name1", "1", "action", "Kõrge");
        rental.add(m1);
        String expectedMessage = "Liiga vähe raha";
        assertThrows(IllegalArgumentException.class, () -> {
            rental.lendMovies("1", 3, "TestUser");
        }, expectedMessage);
    }

    @Test
    public void checkDuplicateEIDRTest() {
        Movie m1 = new Movie("Name1", "1", "ACTION", "Madal");
        String expectedMessage = "EIDR already exists: 1";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Movie m2 = new Movie("Name2", "1", "DRAMA", "Keskmine");
        });
        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }
}


