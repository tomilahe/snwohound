import java.util.*;

public class MovieRental {
    private List<MovieShelf> movieShelves;
    private List<User> users;

    public MovieRental(List<MovieShelf> movieShelves) {
        this.movieShelves = movieShelves;
    }

    public void addUser(User user) {
        if (users == null) {
            users = new ArrayList<>();
        }
        if (users.contains(user)) {
            return;
        }
        users.add(user);
    }


    public void add(Movie movie) {
        for (MovieShelf movieShelf : movieShelves) {
            if (movieShelf.getCategory().equals(movie.getKategooria())) {
                movieShelf.getMovies().add(movie);
                return;
            }
        }

        List<Movie> newMovieshelf = new ArrayList<>();
        newMovieshelf.add(movie);
        movieShelves.add(new MovieShelf(newMovieshelf, movie.getKategooria()));

    }


    public Movie getMovie(String eidr) {
        for (MovieShelf movieShelf : movieShelves) {
            return movieShelf.getMovieByEidr(eidr);
        }
        return null;
    }

    public void removeMovie(String eidr) {
        Movie movie = getMovie(eidr);
        String cat = movie.getKategooria();

        for (MovieShelf movieShelf : movieShelves) {
            if (movieShelf.getCategory().equals(cat)) {
                List<Movie> movies = movieShelf.getMovies();
                movies.remove(movie);
            }
        }

    }

    public void getAllCategories() {
        System.out.println("All Categories");
        for (MovieShelf movieShelf : movieShelves) {
            System.out.println(movieShelf.getCategory());
        }
    }

    public void getAllMovies(String category) {
        for (MovieShelf movieShelf : movieShelves) {
            if (movieShelf.getCategory().equals(category)) {
                System.out.println(movieShelf);
            }
        }
    }

    public void lendMovies(String eidr, int days, String username) {
        int userIdx = users.indexOf(username);
        User user = users.get(userIdx + 1);
        double userMoney = user.getMoney();
        Movie requestedMovie = getMovie(eidr);
        double price = requestedMovie.getLendingPrice(days);
        if (price < userMoney) {
            user.addLendedMovie(requestedMovie);
            user.setMoney(userMoney - price);
            movieShelves.remove(requestedMovie); //Eeldusel, et filmid on füüsilised ja filmi koopiaid on üks
            System.out.println("Film " + requestedMovie + " laenutatud");
        } else {
            throw new IllegalArgumentException("Liiga vähe raha");
        }


    }

    public void lendMovies(String eidr, String username) {
        int userIdx = users.indexOf(username);
        User user = users.get(userIdx);
        double userMoney = user.getMoney();
        Movie requestedMovie = getMovie(eidr);
        double price = requestedMovie.getLendingPrice();

        if (price < userMoney) {
            user.addLendedMovie(requestedMovie);
            user.setMoney(userMoney - price);
            movieShelves.remove(requestedMovie);
            System.out.println("Film " + requestedMovie + " laenutatud");
        } else {
            throw new IllegalArgumentException("Liiga vähe raha");
        }
    }


    @Override
    public String toString() {
        return "Filmilaenutus. Saadavalolevad filmid:" + "\n" + movieShelves + "\n";
    }
}
