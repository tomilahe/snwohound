import java.util.List;

public class User implements Comparable<User> {
    private String nimi;
    private double raha;
    private List<Movie> lendedMovies;

    public User(String nimi, double raha, List<Movie> lendedMovies) {
        this.nimi = nimi;
        this.raha = raha;
        this.lendedMovies = lendedMovies;
    }

    public void removeLendedMovie(Movie movie) {
        lendedMovies.remove(movie);
    }

    @Override
    public int compareTo(User o) {
        return this.nimi.compareTo(o.nimi);
    }

    public void addLendedMovie(Movie movie) {
        if (movie != null) {
            lendedMovies.add(movie);
        }
    }


    public void setMoney(double raha) {
        this.raha = raha;
    }

    public String getName() {
        return nimi;
    }

    public double getMoney() {
        return raha;
    }

    public List<Movie> getLendedMovies() {
        return lendedMovies;
    }
}
