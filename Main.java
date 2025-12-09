import java.util.ArrayList;


public class Main {


    public static void main(String[] args) {
        MovieRental movieRental = new MovieRental(new ArrayList<>());
        Movie movie = new Movie("One", "1", "ACTION", "Madal");
        Movie movie2 = new Movie("Two", "2", "DRAMA", "Keskmine");
        Movie movie3 = new Movie("Three", "3", "DRAMA", "Kõrge");
        Movie movie4 = new Movie("Four", "4", "ACTION", "Madal");
        Movie movie5 = new Movie("Five", "5", "ACTION", "Kõrge");
        movieRental.add(movie);
        System.out.println(movieRental.getMovie("1"));
        movieRental.removeMovie("1");
        System.out.println(movieRental.getMovie("1"));
        movieRental.add(movie2);
        movieRental.add(movie3);
        movieRental.add(movie4);
        movieRental.add(movie5);
        System.out.println(movieRental);
        movieRental.getAllCategories();

    }
}
